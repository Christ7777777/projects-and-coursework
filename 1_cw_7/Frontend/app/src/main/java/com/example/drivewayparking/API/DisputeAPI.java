package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.DisputeRequest;
import com.example.drivewayparking.Model.DisputeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DisputeAPI {

    @POST("disputes")
    Call<Integer> addDispute(@Body DisputeRequest dispute);

    @GET("disputes/getDisputes")
    Call<List<DisputeResponse>> getAllDisputes();

    @GET("disputes/getDispute/Id/{id}")
    Call<DisputeResponse> getDispute(@Path("id")Long id);

    @GET("disputes/getDisputes/Admin/{id}")
    Call<List<DisputeResponse>> getDisputesByAdmin(@Path("id")Long id);

    @PUT("disputes/resolveDispute/{id}")
    Call<Integer> resolveDispute(@Path("id")Long id);
}
