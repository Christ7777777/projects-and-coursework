package com.example.postrequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("v3/3b4fb87a-8dfc-483d-ac13-bb3c94334f23")
    Call<List<PostRequest>> getPosts();
}
