package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.Booking;
import com.example.drivewayparking.Model.BookingRequest;
import com.example.drivewayparking.Model.BookingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * The interface Booking api.
 */
public interface BookingAPI {

    /**
     * New booking call.
     *
     * @param bookingRequest the booking request
     * @return the call
     */
    @PUT("bookings/request")
    Call<BookingResponse> bookingRequest(@Body BookingRequest bookingRequest);

    /**
     * Add booking call.
     *
     * @param booking the booking
     * @return the call
     */
    @POST("bookings/")
    Call<Integer> addBooking(@Body BookingRequest booking);

    /**
     * Gets all bookings.
     *
     * @return the all bookings
     */
    @GET("bookings/")
    Call<List<Booking>> getAllBookings();

    /**
     * Gets booking.
     *
     * @param id the id
     * @return the booking
     */
    @GET("bookings/{id}")
    Call<BookingResponse> getBooking(@Path("id") Long id);

    /**
     * Gets booking by user.
     *
     * @param id the id
     * @return the booking by user
     */
    @GET("bookings/user/{id}")
    Call<List<BookingResponse>> getBookingByUser(@Path("id") Long id);


    /**
     * Delete booking call.
     *
     * @param bid the bid
     * @return the call
     */
    @DELETE("bookings/{bid}")
    Call<Integer> deleteBooking(@Path("bid") int bid);

    /**
     * Update booking call.
     *
     * @param booking the booking
     * @return the call
     */
    @PUT("bookings/")
    Call<Integer> updateBooking(@Body Booking booking);


    /**
     * Gets all bookings for property.
     *
     * @param pid the pid
     * @return the all bookings for property
     */
    @GET("bookings/property/{pid}")
    Call<List<BookingResponse>> getAllBookingsForProperty(@Path("pid") Long pid);

}
