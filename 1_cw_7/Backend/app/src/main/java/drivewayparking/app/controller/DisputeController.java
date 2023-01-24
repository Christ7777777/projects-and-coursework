package drivewayparking.app.controller;

import drivewayparking.app.dto.DisputeRequest;
import drivewayparking.app.dto.DisputeResponse;
import drivewayparking.app.entity.Dispute;
import drivewayparking.app.service.DisputeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "DisputeController", description = "REST APIs for the Dispute Entity")
@RestController
@RequestMapping("/disputes")
public class DisputeController {

    @Autowired
    private DisputeService service;

    @ApiOperation(value = "Get dispute by id", notes = "Get a dispute by the given id from the database", response = Dispute.class)
    @GetMapping("/getDispute/Id/{id}")
    public DisputeResponse getDispute(@PathVariable Long id){
        return service.getDisputeById(id);
    }

    @ApiOperation(value = "Get dispute by booking id", notes = "Get the dispute by the given booking id from the database", response = Dispute.class)
    @GetMapping("/getDispute/BookingId/{id}")
    public DisputeResponse getDisputeByBookingId(@PathVariable Long id){
        return service.getDisputeByBookingId(id);
    }

    @ApiOperation(value = "", hidden = true)
    @GetMapping("/getDisputes")
    public List<DisputeResponse> getDisputes() { return service.getDisputes(); }

    @ApiOperation(value = "Get all the disputes for the admin", notes = "Get all the disputes by the given admin id from the database", response = Iterable.class)
    @GetMapping("/getDisputes/Admin/{id}")
    public List<DisputeResponse> getDisputesByAdmin(@PathVariable Long id) { return service.getDisputesByAdminId(id); }

    @ApiOperation(value = "Get all unresolved disputes for the admin", notes = "Get all the unresolved disputes by the given admin id from the database", response = Iterable.class)
    @GetMapping("/getUnresolvedDisputes/Admin/{id}")
    public List<DisputeResponse> getUnresolvedDisputesByAdmin(@PathVariable Long id) { return service.getUnresolvedDisputesByAdminId(id); }

    @ApiOperation(value = "Create new dispute", notes = "Create new dispute and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PostMapping
    public Integer createDispute(@RequestBody DisputeRequest request){
        return service.saveDispute(request);
    }

    @ApiOperation(value = "Resolve dispute", notes = "Change the status of the dispute to resolved and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PutMapping("/resolveDispute/{id}")
    public Integer resolveDispute(@PathVariable Long id){
        return service.resolveDispute(id);
    }

    @ApiOperation(value = "Delete property", notes = "Delete property by the given id from the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @DeleteMapping("/deleteDispute/{id}")
    public Integer deleteDispute(@PathVariable Long id){ return service.deleteDispute(id); }

}
