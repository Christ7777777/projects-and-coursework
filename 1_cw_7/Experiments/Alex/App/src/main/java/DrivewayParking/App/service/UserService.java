package DrivewayParking.App.service;

import DrivewayParking.App.entity.User;
import DrivewayParking.App.login.LoginResponse;
import DrivewayParking.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user){
        return repository.save(user);
    }

    public Integer saveUsers(List<User> userList){
        repository.saveAll(userList);
        return 0;
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email){
        return repository.findByEmail(email);
    }

    public User updateUser(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);

        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setName(user.getName());
        }

        return repository.save(existingUser);
    }

    public LoginResponse getLogin(String email, String password) {
        User existingUser = repository.findByEmail(email);
        if (existingUser != null) {
            if (existingUser.getPassword().equals(password)) {
                return new LoginResponse(0, existingUser.getPhoneNumber(), existingUser.getName());
            }
            else {
                return new LoginResponse(1, null, null);
            }
        }
        return new LoginResponse(2, null, null);
    }

    public Integer updatePassword(String email, String password) {
        User existingUser = repository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setPassword(password);
        }
        repository.save(existingUser);
        return 0;
    }

    public Integer deleteUserByEmail(String email){
        repository.deleteUserByEmail(email);
        return 0;
    }



}
