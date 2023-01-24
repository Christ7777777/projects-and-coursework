package drivewayparking.app.controller;

import drivewayparking.app.dto.AmazonS3Request;
import drivewayparking.app.dto.AmazonS3Response;
import drivewayparking.app.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/amazonS3")
public class AmazonS3Controller {

    @Autowired
    private AmazonS3Service service;

    @PostMapping
    public Integer uploadFile(@RequestParam(value = "file") MultipartFile file,
                                             @RequestParam(value = "userId", required = false) Long userId,
                                             @RequestParam(value = "propertyId", required = false) Long propertyId,
                                             @RequestParam(value = "disputeId", required = false) Long disputeId,
                                             @RequestParam(value = "adminId", required = false) Long adminId) {
        return service.uploadFile(file, userId, propertyId, disputeId, adminId);
    }

    @PutMapping
    public List<AmazonS3Response> downloadFiles(@RequestBody AmazonS3Request request) {
        return service.downloadFiles(request.getUserId(), request.getPropertyId(), request.getDisputeId(), request.getAdminId());
    }

    @DeleteMapping("/{fileName}")
    public Integer deleteFile(@PathVariable String fileName) { return service.deleteFile(fileName); }

}
