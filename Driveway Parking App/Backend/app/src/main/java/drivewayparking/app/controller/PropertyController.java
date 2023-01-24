package drivewayparking.app.controller;

import drivewayparking.app.dto.PropertyRequest;
import drivewayparking.app.entity.Property;
import drivewayparking.app.service.PropertyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "PropertyController", description = "REST APIs for the Property Entity")
@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @ApiOperation(value = "Get property by id", notes = "Get a property by the given id from the database", response = Property.class)
    @GetMapping("/getProperty/Id/{id}")
    public Property getPropertyById(@PathVariable Long id) { return service.getPropertyById(id); }

    @ApiOperation(value = "Get property by title", notes = "Get a property by the given title from the database", response = Property.class)
    @GetMapping("/getProperty/Title/{title}")
    public Property getPropertyByTitle(@PathVariable String title) { return service.getPropertyByTitle(title); }

    @ApiOperation(value = "Get all properties", notes = "Get all the properties stored in the database", response = Iterable.class)
    @GetMapping("/getProperties")
    public List<Property> getProperties() { return service.getProperties(); }

    @ApiOperation(value = "Get all properties for a user", notes = "Get a list of all properties by the given user id from the database", response = Iterable.class)
    @GetMapping("/getProperties/User/{id}")
    public List<Property> getPropertiesByUserId(@PathVariable Long id){ return service.getPropertiesByUserId(id); }

    @ApiOperation(value = "Get all properties by user request", notes = "Get a list of all properties by location, availability and filters from the database", response = Iterable.class)
    @PutMapping("/getProperties/UserRequest")
    public List<Property> getPropertiesByUserRequest(@RequestBody PropertyRequest request) {

        List<Property> properties = service.getProperties();

        if (request.getLatitude() != null && request.getLongitude() != null && request.getRange() != null) {
            properties = service.getPropertiesByLocation(request.getLatitude(), request.getLongitude(), request.getRange());
        }

        if (properties != null) {
            if (request.getCheck_in() != null && request.getCheck_out() != null)
                properties = service.filterPropertiesByAvailability(properties, request.getCheck_in(), request.getCheck_out());

            if (request.getDriveway() || request.getGarage() || request.getParkingLot() || request.getHandicapped())
                properties = service.filterPropertiesByParkingType(properties, request.getDriveway(), request.getGarage(), request.getParkingLot(), request.getHandicapped());

            if (request.getCar() || request.getTruck() || request.getMotorcycle() || request.getOversized())
                properties = service.filterPropertiesByVehicleType(properties, request.getCar(), request.getTruck(), request.getMotorcycle(), request.getOversized());

            if (request.getEVcharging() || request.getInOut() || request.getTailgating() || request.getShuttle())
                properties = service.filterPropertiesByAmenities(properties, request.getEVcharging(), request.getInOut(), request.getTailgating(), request.getShuttle());
        }

        return properties;
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/getProperties/Location")
    public List<Property> getPropertiesByLocation(@RequestBody PropertyRequest request) {
        return service.getPropertiesByLocation(request.getLatitude(), request.getLongitude(), request.getRange());
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/filterProperties/Availability")
    public List<Property> filterPropertiesByAvailability(@RequestBody PropertyRequest request) {
        return service.filterPropertiesByAvailability(request.getProperties(), request.getCheck_in(), request.getCheck_out());
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/filterProperties/Parking")
    public List<Property> filterPropertiesByParkingType(@RequestBody PropertyRequest request) {
        return service.filterPropertiesByParkingType(request.getProperties(), request.getDriveway(), request.getGarage(), request.getParkingLot(), request.getHandicapped());
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/filterProperties/Vehicle")
    public List<Property> filterPropertiesByVehicleType(@RequestBody PropertyRequest request) {
        return service.filterPropertiesByVehicleType(request.getProperties(), request.getCar(), request.getTruck(), request.getMotorcycle(), request.getOversized());
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/filterProperties/Amenities")
    public List<Property> filterPropertiesByAmenities(@RequestBody PropertyRequest request) {
        return service.filterPropertiesByAmenities(request.getProperties(), request.getEVcharging(), request.getInOut(), request.getInOut(), request.getShuttle());
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/sortProperties/Rating")
    public List<Property> sortPropertiesByRating(@RequestBody List<Property> properties) {
        return service.sortPropertiesByRating(properties);
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/sortProperties/CreationDate")
    public List<Property> sortPropertiesByNewest(@RequestBody List<Property> properties) {
        return service.sortPropertiesByCreationDate(properties);
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/sortProperties/Rate")
    public List<Property> sortPropertiesByRate(@RequestBody List<Property> properties) {
        return service.sortPropertiesByRate(properties);
    }

    @ApiOperation(value = "Create new property", notes = "Create new property and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PostMapping("/")
    public Integer createProperty(@RequestBody PropertyRequest request){
        return service.saveProperty(request);
    }

    @ApiOperation(value = "Update property", notes = "Update some or all attributes of the property and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PutMapping("/updateProperty")
    public Integer updateProperty(@RequestBody PropertyRequest request){
        return service.updateProperty(request);
    }

    @ApiOperation(value = "Approve property", notes = "Change the status of the property to approved and save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    // Admin feature
    @PutMapping("/approveProperty")
    public Integer approveProperty(@RequestBody PropertyRequest request){ return service.approveProperty(request); }

    @ApiOperation(value = "Delete property", notes = "Delete property by the given id from the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @DeleteMapping("/{id}")
    public Integer deleteProperty(@PathVariable Long id){ return service.deleteProperty(id); }

}
