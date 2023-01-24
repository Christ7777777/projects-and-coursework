package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.ImageRequest;
import com.example.drivewayparking.Model.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ImageAPI
{
    @Multipart
    @POST("/amazonS3")
    Call<Integer> uploadImageUser(@Part MultipartBody.Part image, @Part("userId") RequestBody userId);

    @Multipart
    @POST("/amazonS3")
    Call<Integer> uploadImageProperty(@Part MultipartBody.Part image, @Part("propertyId") RequestBody propertyId);

    @Multipart
    @POST("/amazonS3")
    Call<Integer> uploadImageAdmin(@Part MultipartBody.Part image, @Part("adminId") RequestBody adminId);


    @PUT("/amazonS3")
    Call <List<ImageResponse>> downloadImage(@Body ImageRequest imageRequest);

}
