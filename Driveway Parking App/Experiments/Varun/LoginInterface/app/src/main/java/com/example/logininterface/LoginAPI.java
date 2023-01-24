package com.example.logininterface;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginAPI {


    @GET("user")
    Call<Map<String, User>> getUsers();


@POST("addUser")
    Call<ResponseBody> SignupUsers(@Field("Full name") String name,
                                   @Field("email") String mail,
                                   @Field("password") String pass,
                                   @Field("Phone number") String phone);

}
