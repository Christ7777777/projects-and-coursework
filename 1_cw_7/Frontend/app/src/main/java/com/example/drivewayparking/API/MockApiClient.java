package com.example.drivewayparking.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MockApiClient {


    private static final String URL = "https://499bfbfd-6a7a-4839-af0e-90caa06fa916.mock.pstmn.io";

    public static UserAPI getUserApiService(){
        return getRetrofitInstance().create(UserAPI.class);
    }

    public static PropertyAPI getPropertyApiService(){
        return getRetrofitInstance().create(PropertyAPI.class);
    }
    public static UserRatingAPI getUserRatingApiService(){
        return getRetrofitInstance().create(UserRatingAPI.class);
    }

    public static DisputeAPI getDisputeApiService(){
        return getRetrofitInstance().create(DisputeAPI.class);
    }

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
