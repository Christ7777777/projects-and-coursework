package drivewayparking.app.controller;

import drivewayparking.app.dto.AdminRequest;
import drivewayparking.app.entity.Admin;
import drivewayparking.app.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "AdminController", description = "REST APIs for the Admin Entity")
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService service;

    @ApiOperation(value = "Get admin by email", notes = "Get admin by the given email from the database", response = Admin.class)
    @GetMapping("/getAdmin/Email/{email}")
    public Admin getAdmin(@PathVariable String email) {
        return service.getAdminByEmail(email);
    }

    @ApiOperation(value = "Get admin by id", notes = "Get admin by the given id from the database", response = Admin.class)
    @GetMapping("/getAdmin/Id/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        return service.getAdminById(id);
    }

    @ApiOperation(value = "Update admin", notes = "Update some or all attributes of the admin and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PutMapping("/")
    public Integer updateAdmin(@RequestBody AdminRequest request){
        return service.updateAdmin(request);
    }

}

