package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.LoginRequest;
import com.example.drivewayparking.Model.LoginResponse;
import com.example.drivewayparking.Model.UpdatePassword;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.Model.UserRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * The interface User api.
 */
public interface UserAPI {


    /**
     * Gets users.
     *
     * @return the users
     */
    @GET("users/")
    Call<List<User>> getUsers();


    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    @GET("users/getUser/Email/{email}")
    Call<User> getUserByEmail(@Path("email") String email);


    /**
     * Signup users call.
     *
     * @param user the user
     * @return the call
     */
    @POST("users/")
    Call<Integer> SignupUsers(@Body User user);

    /**
     * Gets login.
     *
     * @param loginRequest the login request
     * @return the login
     */
    @PUT("users/login")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

    /**
     * Update password call.
     *
     * @param email    the email
     * @param password the password
     * @return the call
     */
    @PUT("users/updatePassword/{email}")
    Call<Integer> updatePassword(@Path("email") String email,
                                 @Body String password);

    /**
     * Update user call.
     *
     * @param userRequest the user request
     * @return the call
     */
    @PUT("users/updateUser")
    Call<Integer> updateUser(@Body UserRequest userRequest);

    /**
     * Delete user call.
     *
     * @param email the email
     * @return the call
     */
    @DELETE("users/{email}")
    Call<Integer> deleteUser(@Path("email") String email);


}
