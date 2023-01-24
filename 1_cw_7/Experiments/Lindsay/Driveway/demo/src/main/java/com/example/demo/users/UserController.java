package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/getUsers")
    @ResponseBody
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users/createUsers")
    @ResponseBody
    public Users createStudent(@RequestBody Users user) {
        return userRepository.save(user);
        // return "Successfully saved new student " + user.getEmail() + "!";
    }

    @GetMapping("/users/{email}")
    @ResponseBody
    public Users getUserInfo(@PathVariable String email) {
        return userRepository.findById(email).orElse(null);
    }

    @PutMapping({"/users/updatePassword/{email}"})
    @ResponseBody
    public Users updateUserPassword(@PathVariable String email, @RequestBody Users user) {
        Users oldUser = userRepository.findByEmail(email);
        if (oldUser != null) {
            oldUser.setPassword(user.getPassword());
            return userRepository.save(oldUser);
        }
        return null;
        // return "User " + s.getEmail() + " successfully updated!";
    }

    @DeleteMapping({"/users/{email}"})
    @ResponseBody
    public String deleteUser(@PathVariable String email) {
        Users temp = (Users)userRepository.findByEmail(email);
        String id = temp.getId();
        userRepository.deleteById(id);
        return "User with email " + email + " successfully deleted.";
    }

    @GetMapping("/login/{email}")
    @ResponseBody
    public String loginAttempt(@PathVariable String email, @RequestBody Users u) {
        Users user = userRepository.findByEmail(email);

        if (user != null) {
            if (u.getPassword().equals(user.getPassword())) {
                return "Login for user " + u.getEmail() + " successful!";
            }
            else {
                return "Login failed. Password incorrect.";
            }
        }
        return "User with email " + u.getEmail() + " does not exist.";
    }
}