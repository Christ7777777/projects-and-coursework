package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.RatingRequest;
import com.example.drivewayparking.Model.UserRating;
import com.example.drivewayparking.R;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type User rating activity.
 * @author: Varun Advani
 */
public class UserRatingActivity extends AppCompatActivity {

    /**
     * The Back arrow.
     */
    ImageView back_arrow;
    /**
     * The Comments.
     */
    EditText comments;
    /**
     * The Rating bar one.
     */
    RatingBar ratingBar_one,
    /**
     * The Rating bar two.
     */
    ratingBar_two,
    /**
     * The Rating bar three.
     */
    ratingBar_three;
    /**
     * The Submit rating.
     */
    Button submit_rating,
    /**
     * The View all ratings.
     */
    view_all_ratings;
    /**
     * The Progress bar.
     */
    ProgressBar progressBar;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);

        //back_arrow = findViewById(R.id.buttonBackToListing);
        comments =  findViewById(R.id.editTextComment);
        ratingBar_one  = findViewById(R.id.ratingBar1);
        ratingBar_two = findViewById(R.id.ratingBar2);
        ratingBar_three = findViewById(R.id.ratingBar3);
        submit_rating = findViewById(R.id.buttonSubmitRating);
        progressBar = findViewById(R.id.progress_bar_rating);
        view_all_ratings = findViewById(R.id.buttonViewAllRatings);
        random = new Random();
//
//        back_arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), MainActivityHost.class));
//            }
//        });


        submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String review_comments = comments.getText().toString();
                float rating_one = ratingBar_one.getRating();
                float rating_two = ratingBar_two.getRating();
                float rating_three = ratingBar_three.getRating();

                RatingRequest userRating = new RatingRequest();
               userRating.setComments(review_comments);
               userRating.setAccommodation(rating_one);
               userRating.setSafety(rating_two);
               userRating.setResponsiveness(rating_three);
               userRating.setId(getIntent().getExtras().getLong("bid"));

                Call<Integer> call = ApiClient.getUserRatingApiService().newRating(userRating);

                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()) {
                            if (response.body() == 0) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "User Rating saved", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), ViewAllPropertiesActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "User Rating Not Saved", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                           Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        view_all_ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Testing");
                Intent intent = new Intent(getApplicationContext(), ViewAllUserRatingsActivity.class);
                Bundle bundle = new Bundle();
                System.out.println(getIntent().getExtras().getLong("pid") + " pid");
                bundle.putLong("pid", getIntent().getExtras().getLong("pid"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}