package com.example.drivewayparking.Activity;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.Booking;
import com.example.drivewayparking.Model.BookingRequest;
import com.example.drivewayparking.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * The type Create booking activity.
 *
 * @author: Varun Advani
 */
public class CreateBookingActivity extends AppCompatActivity {
    /**
     * The Home.
     */
    ImageView home;
    /**
     * The Title.
     */
    TextView title;
    /**
     * The Start date.
     */
    EditText startDate,
    /**
     * The End date.
     */
    endDate,
    /**
     * The Start time.
     */
    startTime,
    /**
     * The End time.
     */
    endTime;
    /**
     * The Start time.
     */
    ToggleButton start_time,
    /**
     * The End time.
     */
    end_time;
    /**
     * The View bookings.
     */
    Button view_bookings,
    /**
     * The Create booking.
     */
    create_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);


        startDate = findViewById(R.id.editTextListingStartDate);
        endDate = findViewById(R.id.editTextListingEndDate);
        startTime = findViewById(R.id.editTextListingStartTime);
        endTime = findViewById(R.id.editTextListingEndTime);
         view_bookings = findViewById(R.id.viewAllBookings);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivityRenter.class));
            }
        });

        view_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingRequest bookingRequest = new BookingRequest();
                String start = startDate.getText().toString() + " " + startTime.getText().toString();
                String end = endDate.getText().toString() + " " + endTime.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                try {
                    Date start_d = sdf.parse(start);
                    String format_one = output.format(start_d);
                    System.out.println(format_one);
                    bookingRequest.setCheck_in(format_one);
                }
                catch(ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Date end_d = sdf.parse(end);
                    String format_two = output.format(end_d);
                    System.out.println(format_two);
                    bookingRequest.setCheck_out(format_two);
                }
                catch(ParseException e) {
                    e.printStackTrace();
                }

                Call<Integer> call = ApiClient.getBookingApiService().addBooking(bookingRequest);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                          Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                          System.out.println("Error: " + t.getMessage());
                    }
                });
                // Call<Integer> call = ApiClient.getBookingApiService().addBooking()
            }
        });

        view_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          startActivity(new Intent(getApplicationContext(), ViewAllBookingsActivity.class));
            }
        });
    }
}