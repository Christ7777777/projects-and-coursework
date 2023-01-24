package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.BookingResponse;
import com.example.drivewayparking.Model.DisputeResponse;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSpecificDisputeActivity extends AppCompatActivity {

    private long did;
    private long bid;
    private long pid;
    private Boolean isResolved;

    private DisputeResponse dispute;
    private BookingResponse booking;
    private Property property;

    private TextView BookingID;
    private TextView PropertyName;
    private TextView StartTime;
    private TextView EndTime;
    private TextView Message;
    private TextView DisputeID;

    private Button Resolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_dispute);

        BookingID   = findViewById(R.id.BookingID);
        PropertyName= findViewById(R.id.PropertyName);
        StartTime   = findViewById(R.id.StartTime);
        EndTime     = findViewById(R.id.EndTime);
        Message     = findViewById(R.id.Message);
        DisputeID   = findViewById(R.id.DisputeID);

        Resolve = findViewById(R.id.Resolve);
        isResolved = getIntent().getExtras().getBoolean("resolved");

        if(isResolved){
            Resolve.setVisibility(View.GONE);
        }
        else{
            Resolve.setVisibility(View.VISIBLE);
        }

        did = getIntent().getExtras().getLong("did");
        System.out.println(did + " did");
        Call<DisputeResponse> disputeCall = ApiClient.getDisputeApiService().getDispute(did);

        disputeCall.enqueue(new Callback<DisputeResponse>() {
            @Override
            public void onResponse(Call<DisputeResponse> call, Response<DisputeResponse> response) {
                if(response.isSuccessful()) {
                    dispute = response.body();
                    bid = response.body().getBookingId();
                    BookingID.setText("Booking ID: " + dispute.getBookingId());
                    DisputeID.setText("Dispute ID: " + dispute.getId());
                    Message.setText(dispute.getMessage());
                    System.out.println(response.body().toString());
                    Call<BookingResponse> bookingCall = ApiClient.getBookingApiService().getBooking(response.body().getBookingId());

                    bookingCall.enqueue(new Callback<BookingResponse>() {
                        @Override
                        public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                            if(response.isSuccessful()) {
                                booking = response.body();
                                pid = booking.getPid();
                                StartTime.setText("Start: " + booking.getCheck_in().toString());
                                EndTime.setText("End: " + booking.getCheck_out().toString());
                                Call<Property> propertyCall = ApiClient.getPropertyApiService().getPropertyById(response.body().getPid());

                                propertyCall.enqueue(new Callback<Property>() {
                                    @Override
                                    public void onResponse(Call<Property> call, Response<Property> response) {
                                        if (response.isSuccessful()) {
                                            property = response.body();
                                            PropertyName.setText("Name: " + property.getTitle());
                                        }
                                        else{
                                            Toast.makeText(ViewSpecificDisputeActivity.this, "Property Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Property> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "Property Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(ViewSpecificDisputeActivity.this, response.code() + " " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BookingResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Booking Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error getting dispute: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DisputeResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Dispute Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println(bid + " bid");
//        Call<BookingResponse> bookingCall = ApiClient.getBookingApiService().getBooking(bid);
//
//        bookingCall.enqueue(new Callback<BookingResponse>() {
//            @Override
//            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
//                booking = response.body();
//                pid = booking.getPid();
//                StartTime.setText("Start: " + booking.getCheck_in().toString());
//                EndTime.setText("End: " + booking.getCheck_out().toString());
//            }
//
//            @Override
//            public void onFailure(Call<BookingResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Booking Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        System.out.println(pid + " pid");
//        Call<Property> propertyCall = ApiClient.getPropertyApiService().getPropertyById(pid);
//
//        propertyCall.enqueue(new Callback<Property>() {
//            @Override
//            public void onResponse(Call<Property> call, Response<Property> response) {
//                property = response.body();
//                PropertyName.setText("Name: " + property.getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<Property> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Property Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        Resolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Integer> call = ApiClient.getDisputeApiService().resolveDispute(did);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Toast.makeText(getApplicationContext(), "Dispute Resolved Successfully ", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Dispute not Resolved: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}