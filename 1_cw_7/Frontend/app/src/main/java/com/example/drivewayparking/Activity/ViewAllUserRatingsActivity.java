package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.UserRatingAdapter;
import com.example.drivewayparking.Model.RatingResponse;
import com.example.drivewayparking.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type View all user ratings activity.
 * @author: Varun Advani
 */
public class ViewAllUserRatingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView_userRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_user_ratings);

        recyclerView_userRating = findViewById(R.id.user_recycler_view);
        recyclerView_userRating.setLayoutManager(new LinearLayoutManager(this));
       // System.out.println(getIntent().getExtras().getLong("pid") + " pid");

        Call<List<RatingResponse>> call = ApiClient.getUserRatingApiService().getAllPropertyRatings(getIntent().getExtras().getLong("pid"));
        call.enqueue(new Callback<List<RatingResponse>>() {
            @Override
            public void onResponse(Call<List<RatingResponse>> call, Response<List<RatingResponse>> response) {
                if(response.isSuccessful()){
                    System.out.println("Success");
                    UserRatingAdapter userRatingAdapter = new UserRatingAdapter(ViewAllUserRatingsActivity.this, response.body());
                    System.out.println(userRatingAdapter.getItemCount());
                    recyclerView_userRating.setAdapter(userRatingAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<RatingResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}