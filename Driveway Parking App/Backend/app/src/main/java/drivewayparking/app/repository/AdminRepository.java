package drivewayparking.app.repository;

import drivewayparking.app.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

    @Query(value = "SELECT a.* FROM admins a LEFT JOIN disputes d ON a.id = d.admin_id GROUP BY a.id ORDER BY COUNT(d.id) LIMIT 1;", nativeQuery = true)
    Admin findByLowestNumberDisputesAssigned();
}
