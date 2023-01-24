package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.PropertyImageAdapter;
import com.example.drivewayparking.Adapter.UserRatingAdapter;
import com.example.drivewayparking.Model.ImageRequest;
import com.example.drivewayparking.Model.ImageResponse;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.Model.RatingResponse;
import com.example.drivewayparking.Model.UserRating;
import com.example.drivewayparking.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type View specific property activity.
 * @author: Varun Advani
 */
public class ViewSpecificPropertyActivity extends AppCompatActivity {

    private Long pid;
    private TextView propertyName;
    private TextView propertyLocation;
    private TextView propertyHost;
    private TextView propertyStar;
    private Property property;
    private RecyclerView propertyRatings;
    private RecyclerView propertyImages;
    private Double average = 0.0;
    private Double sum = 0.0;
    private static final DecimalFormat decimalFormat    = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_property);

        propertyName = findViewById(R.id.propertyName);
        propertyLocation = findViewById(R.id.propertyLocation);
        propertyHost = findViewById(R.id.propertyHost);
        propertyStar = findViewById(R.id.propertyStars);
        propertyRatings = findViewById(R.id.propertyRatings);
        propertyImages = findViewById(R.id.propertyImages);
        propertyRatings.setLayoutManager(new LinearLayoutManager(ViewSpecificPropertyActivity.this, LinearLayoutManager.HORIZONTAL, false));
        propertyImages.setLayoutManager(new LinearLayoutManager(ViewSpecificPropertyActivity.this, LinearLayoutManager.HORIZONTAL, false));

        pid = getIntent().getExtras().getLong("pid");
        System.out.println(pid + " pid");
        Call<Property> propertyCall = ApiClient.getPropertyApiService().getPropertyById(pid);
        Call<List<RatingResponse>> ratingCall = ApiClient.getUserRatingApiService().getAllPropertyRatings(pid);
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setPropertyId(pid);
        Call<List<ImageResponse>> imageCall = ApiClient.getImageApiService().downloadImage(imageRequest);

        propertyCall.enqueue(new Callback<Property>() {
            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                property = response.body();
                propertyName.setText(property.getTitle());
                propertyLocation.setText(property.getCity() + ", " + property.getState());
                propertyHost.setText(property.getProperty_type());
                //propertyHost.setText(property.getProperty_type() + ", hosted by: " + property.getHost());
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Property Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ratingCall.enqueue(new Callback<List<RatingResponse>>() {
            @Override
            public void onResponse(Call<List<RatingResponse>> call, Response<List<RatingResponse>> response) {
                UserRatingAdapter userRatingAdapter = new UserRatingAdapter(ViewSpecificPropertyActivity.this, response.body());
                System.out.println(userRatingAdapter.getItemCount());
                if(response.body().size() > 0) {
                    propertyRatings.setAdapter(userRatingAdapter);
                    for(RatingResponse rating : response.body()) {
                        average += (rating.getSafety() + rating.getAccommodation() + rating.getResponsiveness())/3.0;
                        sum += average;
                        average = 0.0;
                    }
                    sum /= userRatingAdapter.getItemCount();

                    propertyStar.setText(decimalFormat.format(sum) + "/5.0 (" + userRatingAdapter.getItemCount() + ")");
                }
            }

            @Override
            public void onFailure(Call<List<RatingResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Rating Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Rating Error: " + t.getMessage());
            }
        });

        imageCall.enqueue(new Callback<List<ImageResponse>>() {
            @Override
            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                if(response.isSuccessful()){
                    PropertyImageAdapter propertyImageAdapter = new PropertyImageAdapter(ViewSpecificPropertyActivity.this, response.body());
                    if(response.body().size() > 0){
                        propertyImages.setAdapter(propertyImageAdapter);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), "Image Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}