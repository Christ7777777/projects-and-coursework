package drivewayparking.app.repository;

import drivewayparking.app.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT r.* FROM ratings r JOIN bookings b ON r.id = b.id AND b.pid = :propertyId", nativeQuery = true)
    List<Rating> findByProperty(@Param("propertyId") Long propertyId);

}
