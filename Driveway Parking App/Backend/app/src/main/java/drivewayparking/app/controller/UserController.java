package drivewayparking.app.controller;

import drivewayparking.app.dto.UserRequest;
import drivewayparking.app.dto.LoginRequest;
import drivewayparking.app.entity.User;
import drivewayparking.app.dto.LoginResponse;
import drivewayparking.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "UserController", description = "REST APIs for the User Entity")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @ApiOperation(value = "Get user by email", notes = "Get a user by the given email from the database", response = User.class)
    @GetMapping("/getUser/Email/{email}")
    public User getUser(@PathVariable String email) { return service.getUserByEmail(email); }

    @ApiOperation(value = "Get user by id", notes = "Get a user by the given id from the database", response = User.class)
    @GetMapping("/getUser/Id/{id}")
    public User getUser(@PathVariable Long id) { return service.getUserById(id); }

    @ApiOperation(value = "Get all users", notes = "Get a list of all users from the database", response = Iterable.class)
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return service.getUsers();
    }

    @ApiOperation(value = "Create new user", notes = "Create new user and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PostMapping("/")
    public Integer createUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @ApiOperation(value = "", hidden = true)
    @PostMapping("/createUsers")
    public Integer createUsers(@RequestBody List<User> userList){ return service.saveUsers(userList); }

    @ApiOperation(value = "Login activity", notes = "Grant or deny login based on the provided credentials upon the initial start of the application", response = LoginResponse.class)
    @PutMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return service.login(request);
    }

    @ApiOperation(value = "Update user", notes = "Update some or all attributes of the user and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PutMapping("/updateUser")
    public Integer updateUser(@RequestBody UserRequest request){
        return service.updateUser(request);
    }

    // Admin feature
    @ApiOperation(value = "Verify user", notes = "Change the status of the user government id to verified and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PutMapping("/verifyUser")
    public Integer verifyUser(@RequestBody UserRequest request) {
        return service.verifyUser(request);
    }

    @ApiOperation(value = "Delete user", notes = "Delete user by the given id from the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @DeleteMapping("/{id}")
    public Integer deleteUser(@PathVariable Long id){
        return service.deleteUser(id);
    }

}
