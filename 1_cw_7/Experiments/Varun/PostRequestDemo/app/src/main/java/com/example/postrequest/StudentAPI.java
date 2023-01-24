package com.example.postrequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentAPI {

    @GET("v3/c72ae014-5bfa-458a-96cd-785067cde803")
    Call<List<Student>> getStudents();
}
