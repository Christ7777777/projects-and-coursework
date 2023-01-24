package drivewayparking.app.repository;


import drivewayparking.app.entity.AmazonS3Ref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmazonS3RefRepository extends JpaRepository<AmazonS3Ref, Long> {
    @Query(value = "SELECT r.* FROM amazons3refs r WHERE r.entity_id = :entityId ORDER BY created DESC", nativeQuery = true)
    List<AmazonS3Ref> findByEntityId(@Param("entityId") String entityId);

    AmazonS3Ref findByFileName(String fileName);
}
