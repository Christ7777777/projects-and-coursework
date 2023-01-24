package com.example.drivewayparking.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Api client.
 */
public class ApiClient {

    private static final String URL = "http://coms-309-015.class.las.iastate.edu:8080/";
    //private static final String URL = "http://10.0.2.2:8080/";

    /**
     * Get user api service user api.
     *
     * @return the user api
     */
    public static UserAPI getUserApiService(){
        return getRetrofitInstance().create(UserAPI.class);
    }

    /**
     * Get property api service property api.
     *
     * @return the property api
     */
    public static PropertyAPI getPropertyApiService(){
        return getRetrofitInstance().create(PropertyAPI.class);
    }

    /**
     * Get user rating api service user rating api.
     *
     * @return the user rating api
     */
    public static UserRatingAPI getUserRatingApiService(){
        return getRetrofitInstanceBooking().create(UserRatingAPI.class);
    }

    /**
     * Get booking api service booking api.
     *
     * @return the booking api
     */
    public static BookingAPI getBookingApiService(){
        return getRetrofitInstance().create(BookingAPI.class);
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
    private static Retrofit getRetrofitInstanceBooking(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ImageAPI getImageApiService(){
        return getRetrofitInstance().create(ImageAPI.class);
    }

    public static AdminAPI getAdminApiService(){
        return getRetrofitInstance().create(AdminAPI.class);
    }
}
