package drivewayparking.app.repository;

import drivewayparking.app.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property findByTitle(String title);

    @Query("SELECT p FROM Property p WHERE p.user.id = :userId")
    List<Property> findByUser(@Param("userId") Long userId);

    @Query(value = "SELECT p.* FROM properties p WHERE ABS(p.latitude - :latitude) < 0.1 AND ABS(p.longitude - :longitude) < 0.1 LIMIT 1", nativeQuery = true)
    Property findByMinuteLatitudeAndLongitude(@Param("latitude") Double latitude, @Param("longitude") Double longitude);

    @Query(value = "SELECT p.* FROM properties p WHERE ABS(p.latitude - :latitude) < 1 AND ABS(p.longitude - :longitude) < 1 LIMIT 1", nativeQuery = true)
    Property findByDegreeLatitudeAndLongitude(@Param("latitude") Double latitude, @Param("longitude") Double longitude);

    @Query("SELECT p FROM Property p WHERE p.city = :city AND p.state = :state")
    List<Property> findAllByCityState(@Param("city") String city, @Param("state") String state);

}
