package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.FilterReponse;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.Model.PropertyRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * The interface Property api.
 */
public interface PropertyAPI {


    /**
     * Gets all properties.
     *
     * @return the all properties
     */
    @GET("properties/getProperties")
    Call<List<Property>> getAllProperties();

    /**
     * Gets property by user id.
     *
     * @param id the id
     * @return the property by user id
     */
    @GET("properties/getProperties/User/{id}")
    Call<List<Property>> getPropertyByUserId(@Path("id") Long id);

    /**
     * Gets property by id.
     *
     * @param id the id
     * @return the property by id
     */
    @GET("properties/getProperty/Id/{id}")
    Call<Property> getPropertyById(@Path("id") Long id);

    /**
     * Gets properties by user request.
     *
     * @param propertyRequest the property request
     * @return the properties by user request
     */
    @PUT("properties/getProperties/UserRequest")
    Call<List<Property>> getPropertiesByUserRequest(@Body PropertyRequest propertyRequest);

    /**
     * Add property call.
     *
     * @param property the property
     * @return the call
     */
    @POST("properties/")
    Call<Integer> addProperty(@Body PropertyRequest property);

    /**
     * Delete property call.
     *
     * @param id the id
     * @return the call
     */
    @DELETE("properties/{id}")
    Call<Integer> deleteProperty(@Path("id") int id);

    /**
     * Update property call.
     *
     * @param property the property
     * @return the call
     */
    @PUT("properties/")
    Call<Property> updateProperty(@Body Property property);

    /**
     * Filter property list call.
     *
     * @param filterCall the filter call
     * @return the call
     */
    @GET("properties/")
    Call<List<Property>> filterPropertyList(@Body FilterReponse filterCall);

    /**
     * Gets property by id.
     *
     * @param id the id
     * @return the property by id
     */
    @GET("properties/getProperty/Id/{id}")
    Call<Property> getPropertyById(@Path("id") int id);

    /**
     * Sort properties by rate call.
     *
     * @param properties the properties
     * @return the call
     */
    @PUT("properties/sortProperties/Rate")
    Call<List<Property>> sortPropertiesByRate(@Body List<Property> properties);

    /**
     * Sort properties by rating call.
     *
     * @param properties the properties
     * @return the call
     */
    @PUT("properties/sortProperties/Rating")
    Call<List<Property>> sortPropertiesByRating(@Body List<Property> properties);
}
