package drivewayparking.app.repository;

import drivewayparking.app.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.user.id = :id")
    List<Booking> findByUser(@Param("id") Long id);

    @Query("SELECT b FROM Booking b WHERE b.property.id = :id")
    List<Booking> findByProperty(@Param("id") Long id);

    @Query(value = " SELECT count(*) FROM bookings b WHERE b.pid = :pid AND (b.check_out < :check_in OR b.check_in > :check_out)", nativeQuery = true)
    int findNonConflictingBookings(@Param("pid") Long pid, @Param("check_in") Timestamp check_in,
                                             @Param("check_out") Timestamp check_out);

}
