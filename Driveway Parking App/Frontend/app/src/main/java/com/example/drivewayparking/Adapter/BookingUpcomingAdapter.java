package com.example.drivewayparking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Activity.UserRatingActivity;
import com.example.drivewayparking.Model.Booking;
import com.example.drivewayparking.Model.BookingResponse;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Booking upcoming adapter.
 * @author: Varun Advani
 */
public class BookingUpcomingAdapter extends RecyclerView.Adapter<BookingUpcomingAdapter.BookingViewHolder> {

    private final Context context;
    private final List<BookingResponse> bookingList;


    /**
     * Instantiates a new Booking upcoming adapter.
     *
     * @param context     the context
     * @param bookingList the booking list
     */
    public BookingUpcomingAdapter(Context context, List<BookingResponse> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_list_view_upcoming, parent, false);
        return new BookingUpcomingAdapter.BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        final BookingResponse bookingResponse = bookingList.get(position);
        Call<Property> call = ApiClient.getPropertyApiService().getPropertyById(bookingResponse.getPid());
        call.enqueue(new Callback<Property>() {
            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                if(response.isSuccessful()){
                    holder.propertyName.setText(response.body().getTitle());
                }
                else{
                    Toast.makeText(context.getApplicationContext(),"Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.startDate.setText(bookingResponse.getCheck_in().toString());
        holder.endDate.setText(bookingResponse.getCheck_out().toString());

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    /**
     * The type Booking view holder.
     */
    static class BookingViewHolder extends RecyclerView.ViewHolder{
        /**
         * The Property name.
         */
        TextView propertyName,
        /**
         * The Start date.
         */
        startDate,
        /**
         * The End date.
         */
        endDate,
        /**
         * The Rate property.
         */
        rateProperty;


        /**
         * Instantiates a new Booking view holder.
         *
         * @param itemView the item view
         */
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.textView_bookedPropertyName_upcoming);
            startDate = itemView.findViewById(R.id.textView_start_date_upcoming);
            endDate = itemView.findViewById(R.id.textView_end_date_upcoming);
        }
    }
}
