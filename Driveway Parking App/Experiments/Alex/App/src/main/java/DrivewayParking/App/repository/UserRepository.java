package DrivewayParking.App.repository;

import DrivewayParking.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    void deleteUserByEmail(String email);
}
