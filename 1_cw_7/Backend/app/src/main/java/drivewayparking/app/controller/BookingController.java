package drivewayparking.app.controller;

import drivewayparking.app.dto.BookingRequest;
import drivewayparking.app.dto.BookingResponse;
import drivewayparking.app.entity.Booking;
import drivewayparking.app.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Api(value = "BookingController", description = "REST APIs for the Booking Entity")
@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService service;

    @ApiOperation(value = "Add a booking", notes = "Save a booking to the database; property " +
            "becomes unavailable during time of booking", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 1, message = "Error"),
            @ApiResponse(code = 2, message = "Property Not Found"),
            @ApiResponse(code = 3, message = "Conflicting Booking"),
            @ApiResponse(code = 4, message = "Invalid Time Request")})
    @PostMapping("/")
    public Integer addBooking(@RequestBody BookingRequest booking) {
        return service.saveBooking(booking);
    }

    @ApiOperation(value = "", hidden = true)
    @PutMapping("/request")
    public BookingResponse bookingRequest(@RequestBody BookingRequest request) {
        return service.bookingRequest(request);
    }

    @ApiOperation(value = "Get information regarding a booking", notes = "Request booking information based on unique " +
            "booking id", response = BookingResponse.class)
    @GetMapping("/{id}")
    public BookingResponse getBooking(@PathVariable Long id) {
        Booking b = service.getBookingById(id);
        return service.bookingToResponse(b);
    }

    @ApiOperation(value = "Get all bookings for a user", notes = "Get a list of all bookings for a user based " +
            "on unique user id", response = Iterable.class)
    @GetMapping("/user/{id}")
    public List<BookingResponse> getBookingsByUser(@PathVariable Long id) {
        List<Booking> bookings = service.getBookingsByUser(id);
        List<BookingResponse> result = service.bookingsToResponse(bookings);

        Collections.sort(result);

        return result;
    }

    @ApiOperation(value = "Get all bookings for a property", notes = "Get a list of all bookings for a property based " +
            "on unique property id", response = Iterable.class)
    @GetMapping("/property/{id}")
    public List<BookingResponse> getBookingsByProperty(@PathVariable Long id) {
        List<Booking> bookings = service.getBookingsByProperty(id);
        List<BookingResponse> result = service.bookingsToResponse(bookings);

        Collections.sort(result);

        return result;
    }

    @ApiOperation(value = "Get all bookings", notes = "Get all bookings stored in the database", response = Iterable.class)
    @GetMapping("/")
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = service.getAllBookings();
        List<BookingResponse> result = new ArrayList<>();

        if (bookings.size() > 0) {
            result = service.bookingsToResponse(bookings);
        }

        return result;
    }

    @ApiOperation(value = "Delete booking", notes = "Delete booking by the given id from the database", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 5, message = "Booking Not Found") })
    @DeleteMapping("/{bid}")
    public int deleteBooking(@PathVariable Long bid) {
        return service.deleteBooking(bid);
    }

    @ApiOperation(value = "Update booking", notes = "Update a booking by the given id, provided " +
            "there is not a conflicting booking", response = Integer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "OK"),
            @ApiResponse(code = 3, message = "Conflicting Booking Exists") })
    @PutMapping("/")
    public Integer updateBooking(@RequestBody Booking booking) {
        return service.updateBooking(booking);
    }
}
