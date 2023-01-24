package drivewayparking.app.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import drivewayparking.app.dto.AmazonS3Response;
import drivewayparking.app.entity.AmazonS3Ref;
import drivewayparking.app.enums.EntityType;
import drivewayparking.app.enums.Status;
import drivewayparking.app.repository.AmazonS3RefRepository;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class AmazonS3Service {

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private AmazonS3RefRepository repository;

    private final static int USER_ALLOWED_FILE_LIMIT = 1;
    private final static int PROPERTY_ALLOWED_FILE_LIMIT = 10;
    private final static int DISPUTE_ALLOWED_FILE_LIMIT = 3;
    private final static int ADMIN_ALLOWED_FILE_LIMIT = 1;

    public Integer uploadFile(MultipartFile file, Long userId, Long propertyId, Long disputeId, Long adminId) {

        // create entity reference id
        String entityId = null;
        if (userId != null) entityId = EntityType.User.getValue() + "_" + userId;
        else if (propertyId != null) entityId = EntityType.Property.getValue() + "_" + propertyId;
        else if (disputeId != null) entityId = EntityType.Dispute.getValue() + "_" + disputeId;
        else if (adminId != null) entityId = EntityType.Admin.getValue() + "_" + adminId;

        if (file != null && entityId != null) {
            // save file to AmazonS3 server
            File convertedFile = convertMultipartFileToFile(file);
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, convertedFile));
            convertedFile.delete();

            // save entity reference and file name to the repository
            AmazonS3Ref reference = new AmazonS3Ref();
            reference.setEntityId(entityId);
            reference.setFileName(fileName);
            repository.save(reference);

            deleteSurplusFiles(entityId);

            return Status.OK.getValue();
        }
        return Status.Error.getValue();
    }

    public List<AmazonS3Response> downloadFiles(Long userId, Long propertyId, Long disputeId, Long adminId) {

        String entityId = null;
        if (userId != null) entityId = EntityType.User.getValue() + "_" + userId;
        else if (propertyId != null) entityId = EntityType.Property.getValue() + "_" + propertyId;
        else if (disputeId != null) entityId = EntityType.Dispute.getValue() + "_" + disputeId;
        else if (adminId != null) entityId = EntityType.Admin.getValue() + "_" + adminId;

        if (entityId != null) {
            List<AmazonS3Ref> fileReferences = repository.findByEntityId(entityId);
            List<AmazonS3Response> contentsList = new ArrayList<>();
            for (AmazonS3Ref reference : fileReferences) {

                // fetch the file name for the requested entity
                String fileName = reference.getFileName();

                S3Object s3Object = s3Client.getObject(bucketName, fileName);
                S3ObjectInputStream inputStream = s3Object.getObjectContent();
                try {
                    byte[] content = IOUtils.toByteArray(inputStream);
                    String stringEncodedContent = Base64.getEncoder().encodeToString(content);
                    contentsList.add(new AmazonS3Response(fileName, stringEncodedContent));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return contentsList;
        }
        return null;
    }

    public Integer deleteFile(String fileName) {

        if (fileName != null) {
            AmazonS3Ref reference = repository.findByFileName(fileName);

            if (reference != null) {
                repository.deleteById(reference.getId());
                s3Client.deleteObject(bucketName, fileName);
                return Status.OK.getValue();
            }
            return Status.Not_Found.getValue();
        }
        return Status.Error.getValue();
    }

    // Helper method
    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getName());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    // Helper method
    private void deleteSurplusFiles(String entityId) {

        Integer entityType = Integer.parseInt(entityId.substring(0, 1));

        int allowedLimit = 0;
        switch (entityType) {
            case 0: allowedLimit = USER_ALLOWED_FILE_LIMIT;
                    break;
            case 1: allowedLimit = PROPERTY_ALLOWED_FILE_LIMIT;
                    break;
            case 2: allowedLimit = DISPUTE_ALLOWED_FILE_LIMIT;
                    break;
            case 4: allowedLimit = ADMIN_ALLOWED_FILE_LIMIT;
                    break;
        }

        List<AmazonS3Ref> fileReferences = repository.findByEntityId(entityId);
        AmazonS3Ref[] fileReferencesArray = new AmazonS3Ref[fileReferences.size()];
        fileReferences.toArray(fileReferencesArray);
        int numRef = fileReferencesArray.length;

        for (int i = allowedLimit; i < numRef; ++i) {
            deleteFile(fileReferencesArray[i].getFileName());
        }
    }
}
