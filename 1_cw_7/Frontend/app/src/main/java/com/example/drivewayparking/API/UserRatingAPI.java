package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.RatingRequest;
import com.example.drivewayparking.Model.RatingResponse;
import com.example.drivewayparking.Model.UserRating;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * The interface User rating api.
 */
public interface UserRatingAPI {

    /**
     * New rating call.
     *
     * @param userRating the user rating
     * @return the call
     */
    @POST("ratings/")
    Call<Integer> newRating(@Body RatingRequest userRating);

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    @GET("ratings/")
    Call<List<UserRating>> getRatings();

    /**
     * Gets rating.
     *
     * @param id the id
     * @return the rating
     */
    @GET("ratings/{id}")
    Call<UserRating> getRating(@Path("id") Long id);

    /**
     * Gets rating by pid.
     *
     * @param id the id
     * @return the rating by pid
     */
    @GET("ratings/property/{id}")
    Call<List<UserRating>> getRatingByPid(@Path("id")long id);

    /**
     * Delete rating call.
     *
     * @param id the id
     * @return the call
     */
    @DELETE("ratings/{id}")
    Call<Void> deleteRating(@Path("id") Long id);

    /**
     * Gets all property ratings.
     *
     * @param id the id
     * @return the all property ratings
     */
    @GET("ratings/property/{id}")
    Call<List<RatingResponse>> getAllPropertyRatings(@Path("id") Long id);
}
