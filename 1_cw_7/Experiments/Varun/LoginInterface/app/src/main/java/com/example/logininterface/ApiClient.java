package com.example.logininterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String URL = "https://10.0.2.2:8080/";

    public static LoginAPI getLoginApiService(){
        return getRetrofitInstance().create(LoginAPI.class);
    }

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
