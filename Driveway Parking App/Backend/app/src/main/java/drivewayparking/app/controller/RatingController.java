package drivewayparking.app.controller;

import drivewayparking.app.dto.RatingRequest;
import drivewayparking.app.dto.RatingResponse;
import drivewayparking.app.service.RatingService;
import drivewayparking.app.entity.Rating;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "RatingController", description = "REST APIs for the Rating Entity")
@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService service;

    @ApiOperation(value = "Add a rating", notes = "Save a rating to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Error")})
    @PostMapping("/")
    public Integer addRating(@RequestBody RatingRequest rating) {
        return service.saveRating(rating);
    }

    @ApiOperation(value = "Get a rating for a booking", notes = "Request rating information based on unique " +
            "booking id value", response = Rating.class)
    @GetMapping("/{id}")
    public Rating getRating(@PathVariable Long id){
        return service.getRatingById(id);
    }

    @ApiOperation(value = "Get all ratings for a given property", notes = "Request all ratings for a property based " +
            "on unique property id", response = Iterable.class)
    @GetMapping("/property/{id}")
    public List<RatingResponse> getAllPropertyRatings(@PathVariable Long id) {
        List<Rating> ratings = service.getRatingsByProperty(id);

        return service.RatingsToResponse(ratings);
    }

    @ApiOperation(value = "Get all ratings", notes = "Get all ratings stored in the database", response = Iterable.class)
    @GetMapping("/")
    public List<RatingResponse> getAllRatings() {
        List<Rating> all = service.getAllRatings();

        return service.RatingsToResponse(all);
    }

    @ApiOperation(value = "Delete rating", notes = "Delete rating by the given id from the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Error") })
    @DeleteMapping("/{id}")
    public Integer deleteRating(@PathVariable Long id)
    {
        return service.deleteRating(id);
    }


    @ApiOperation(value = "Update rating", notes = "Update some or all attributes of a rating and " +
            "save it to the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Not Found"),
            @ApiResponse(code = 2, message = "Error") })
    @PutMapping("/")
    public Integer updateRating(@RequestBody Rating rating)
    {
        return service.updateRating(rating);
    }

}
