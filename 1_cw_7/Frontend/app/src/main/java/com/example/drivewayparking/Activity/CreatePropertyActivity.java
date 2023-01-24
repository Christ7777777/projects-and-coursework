package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.Address;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.Model.PropertyRequest;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Create property activity.
 * @author: Varun Advani
 */
public class CreatePropertyActivity extends AppCompatActivity {


    /**
     * The Back.
     */
    ImageView back;
    /**
     * The Title.
     */
    EditText title,
    /**
     * The Street.
     */
    street,
    /**
     * The City.
     */
    city,
    /**
     * The State.
     */
    state,
    /**
     * The Country.
     */
    country,
    /**
     * The Postal code.
     */
    postal_code,
    /**
     * The Rate.
     */
    rate;
    /**
     * The Hourly.
     */
    ToggleButton hourly;


    /**
     * The Create property.
     */
    Button create_property;
    /**
     * The View all properties.
     */
    Button view_all_properties;
    /**
     * The Progress bar.
     */
    ProgressBar progressBar;
    /**
     * The Address list.
     */
    List<android.location.Address> addressList;
    private String email;
    private Long user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_property);

       // back = findViewById(R.id.home_button);
        title = findViewById(R.id.property_name);
        street = findViewById(R.id.street_address);
        city = findViewById(R.id.city_name);
        state = findViewById(R.id.state_name);
        country = findViewById(R.id.country_name);
        postal_code = findViewById(R.id.postal_code);
        rate = findViewById(R.id.hourlyrate);
        create_property = findViewById(R.id.create_new_property);
        view_all_properties = findViewById(R.id.view_all_properties);
        progressBar = findViewById(R.id.progress_bar_property);
        email = getIntent().getExtras().getString("f_email");
        if(email != null) {
            Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                       user_id = response.body().getId();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                       Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        System.out.println(email);

//        Call<>


//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), MainActivityHost.class));
//            }
//        });
        create_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
               String property_title = title.getText().toString().trim();
               String street_name = street.getText().toString().trim();
               String city_name = city.getText().toString().trim();
               String state_name = state.getText().toString().trim();
               String country_name = country.getText().toString().trim();
               String postal = postal_code.getText().toString().trim();
               double property_rate = Double.parseDouble(rate.getText().toString().trim());
               Geocoder geocoder = new Geocoder(CreatePropertyActivity.this, Locale.getDefault());
                try {
                    addressList = geocoder.getFromLocationName(street_name + ", " + city_name + ", " + state_name + " " + postal + ", " + country_name, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addressList.size() == 0) {
                    Toast.makeText(CreatePropertyActivity.this, "No address found", Toast.LENGTH_SHORT).show();
                }

                PropertyRequest property = new PropertyRequest();
                property.setRatePerHour(property_rate);
                property.setTitle(property_title);
                property.setCity(city_name);
                property.setStreet(street_name);
                property.setState(state_name);
                property.setCountry(country_name);
                property.setZipcode(Integer.parseInt(postal));
                property.setLatitude(addressList.get(0).getLatitude());
                property.setLongitude(addressList.get(0).getLongitude());
                property.setUserId(user_id);
                addressList.remove(0);
                Call<Integer> call = ApiClient.getPropertyApiService().addProperty(property);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                          if(response.isSuccessful()){
                              progressBar.setVisibility(View.GONE);
                              Toast.makeText(getApplicationContext(), "Property Created Successfully ", Toast.LENGTH_LONG).show();
                          }
                          else{
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
        view_all_properties.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CreatePropertyActivity.this, ViewAllUserPropertyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("user_id", user_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}