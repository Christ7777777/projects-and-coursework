package com.example.drivewayparking.API;

import com.example.drivewayparking.Model.Admin;
import com.example.drivewayparking.Model.AdminRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminAPI {

@GET("admins/getAdmin/Email/{email}")
Call<Admin> getAdminByEmail(@Path("email") String email);

@GET("admins/getAdmin/Id/{id}")
Call<Admin> getAdminById(@Path("id") String id);

@PUT("admins/")
Call<Integer> updateAdmin(@Body AdminRequest adminRequest);

}
