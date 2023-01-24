package drivewayparking.app.repository;

import drivewayparking.app.entity.Admin;
import drivewayparking.app.entity.Dispute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisputeRepository extends JpaRepository<Dispute, Long> {

    @Query(value = "SELECT * FROM disputes WHERE admin_id = :adminId ORDER BY created DESC", nativeQuery = true)
    List<Dispute> findByAdmin(@Param("adminId") Long adminId);

    @Query(value = "SELECT * FROM disputes WHERE admin_id = :adminId AND resolved = FALSE ORDER BY created DESC", nativeQuery = true)
    List<Dispute> findUnresolvedByAdmin(@Param("adminId") Long adminId);

    @Query("SELECT d FROM Dispute d WHERE d.booking.id = :bookingId")
    Dispute findByBooking(@Param("bookingId") Long bookingId);


}
