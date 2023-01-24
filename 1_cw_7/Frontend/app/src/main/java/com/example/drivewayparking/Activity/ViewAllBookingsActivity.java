package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.BookingUpcomingAdapter;
import com.example.drivewayparking.Model.BookingResponse;
import com.example.drivewayparking.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type View all bookings activity.
 * @author: Varun Advani
 */
public class ViewAllBookingsActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookingUpcomingAdapter bookingUpcomingAdapter;
    private List<BookingResponse> bookings;
    private Long pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bookings);
        searchView = findViewById(R.id.search_bar_bookings_host);
        recyclerView = findViewById(R.id.recyclerView_bookings_host);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pid = getIntent().getExtras().getLong("pid");

        Call<List<BookingResponse>> call = ApiClient.getBookingApiService().getAllBookingsForProperty(pid);
        call.enqueue(new Callback<List<BookingResponse>>() {
            @Override
            public void onResponse(Call<List<BookingResponse>> call, Response<List<BookingResponse>> response) {
                if(response.isSuccessful()){
                    bookings = response.body();
                    bookingUpcomingAdapter = new BookingUpcomingAdapter(ViewAllBookingsActivity.this, bookings);
                    recyclerView.setAdapter(bookingUpcomingAdapter);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookingResponse>> call, Throwable t) {
                   Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}