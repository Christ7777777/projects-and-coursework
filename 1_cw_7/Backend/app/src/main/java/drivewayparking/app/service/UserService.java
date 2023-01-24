package drivewayparking.app.service;

import drivewayparking.app.dto.LoginRequest;
import drivewayparking.app.dto.UserRequest;
import drivewayparking.app.entity.Admin;
import drivewayparking.app.entity.User;
import drivewayparking.app.enums.Status;
import drivewayparking.app.dto.LoginResponse;
import drivewayparking.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AdminService adminService;;

    public User getUserByEmail(String email){ return repository.findByEmail(email); }

    public User getUserById(Long id) { return repository.findById(id).orElse(null); }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public Integer saveUser(User user) {

        // checking if the user is already an existing admin
        Admin existingAdmin = adminService.getAdminByEmail(user.getEmail());
        if (existingAdmin != null) {
            return Status.Error.getValue();
        }

        // checking if the user already exists
        User existingUser = repository.findByEmail(user.getEmail());
        if (existingUser == null) {
            repository.save(user);
            return Status.OK.getValue();
        }

        return Status.Error.getValue();
    }

    public Integer saveUsers(List<User> userList) {
        int status = 0;
        for (User user : userList) {
            status += saveUser(user);
        }
        if (status == 0) return Status.OK.getValue();
        else return Status.Error.getValue();
    }

    public LoginResponse login(LoginRequest request) {

        // removing quotation marks around password string
        String password = request.getPassword();
        String email = request.getEmail();

        if (password.charAt(0) == '"') {
            password = password.substring(1, password.length() - 1);
        }

        // checking if the provided email belongs to an existing admin
        Admin existingAdmin = adminService.getAdminByEmail(email);
        if (existingAdmin != null) {

            if (existingAdmin.getPassword().equals(password)) return new LoginResponse(Status.OK.getValue(), true);
            else return new LoginResponse(Status.Error.getValue(), true);

        } // not an admin, so find a user
        else {
            User existingUser = repository.findByEmail(email);
            if (existingUser != null) {

                if (existingUser.getPassword().equals(password)) return new LoginResponse(Status.OK.getValue(), false);
                else return new LoginResponse(Status.Error.getValue(), false);

            }
            return new LoginResponse(Status.Not_Found.getValue(), false);
        }
    }

    // This method updates all the user attributes at once
    public Integer updateUser(UserRequest request) {

        User existingUser = null;
        if (request.getId() != null)
            existingUser = repository.findById(request.getId()).orElse(null);
        else if (request.getEmail() != null) existingUser = repository.findByEmail(request.getEmail());

        if (existingUser != null) {

            if (request.getPassword() != null) existingUser.setPassword(request.getPassword());
            if (request.getPhoneNumber() != null) existingUser.setPhoneNumber(request.getPhoneNumber());
            if (request.getFirstName() != null) existingUser.setFirstName(request.getFirstName());
            if (request.getLastName() != null) existingUser.setLastName(request.getLastName());
            if (request.getGender() != null) existingUser.setGender(request.getGender());
            if (request.getDateOfBirth() != null) existingUser.setDateOfBirth(request.getDateOfBirth());
            if (request.getEmergencyContact() != null) existingUser.setEmergencyContact(request.getEmergencyContact());

            repository.save(existingUser);
            return Status.OK.getValue();
        }

        return Status.Not_Found.getValue();
    }

    public Integer verifyUser(UserRequest request) {

        User existingUser = repository.findById(request.getId()).orElse(null);
        if (existingUser != null) {

            existingUser.setGovernmentID(request.getGovernmentID());
            return Status.OK.getValue();
        }

        return Status.Not_Found.getValue();
    }

    public Integer deleteUser(Long id) {
        User existingUser = repository.findById(id).orElse(null);

        if (existingUser != null) {
            repository.deleteById(id);
            return Status.OK.getValue();
        }
        return Status.Not_Found.getValue();
    }
}
