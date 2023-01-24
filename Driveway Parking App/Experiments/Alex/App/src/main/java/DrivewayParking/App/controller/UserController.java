package DrivewayParking.App.controller;

import DrivewayParking.App.entity.User;
import DrivewayParking.App.login.LoginResponse;
import DrivewayParking.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getUserByEmail(@PathVariable String email){
        return service.getUserByEmail(email);
    }

//    @GetMapping("/userById/{id}")
//    public User getUserById(@PathVariable int id){
//        return service.getUserById(id);
//    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @PostMapping("/createUsers")
    public Integer createUsers(@RequestBody List<User> userList){
        return service.saveUsers(userList);
    }

//    @PutMapping("/updateUser")
//    public User updateUser(@RequestBody User user){
//        return service.updateUser(user);
//    }

    @PutMapping("/login/{email_id}")
    public LoginResponse getLogin(@PathVariable String email_id, @RequestBody String password){
        return service.getLogin(email_id, password);
    }

    @PutMapping("/updatePassword/{email_id}")
    public Integer updatePassword(@PathVariable String email_id, @RequestBody String password){
        return service.updatePassword(email_id, password);
    }

    @DeleteMapping("/delete/{email_id}")
    public Integer deleteUser(@PathVariable String email_id){
        return service.deleteUserByEmail(email_id);
    }

}
