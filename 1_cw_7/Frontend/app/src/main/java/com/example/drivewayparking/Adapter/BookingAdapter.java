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
import com.example.drivewayparking.Activity.CreateDisputeActivity;
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
 * The type Booking adapter.
 * @author: Varun Advani
 */
public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

private final Context context;
private final List<BookingResponse> bookingList;


    /**
     * Instantiates a new Booking adapter.
     *
     * @param context     the context
     * @param bookingList the booking list
     */
    public BookingAdapter(Context context, List<BookingResponse> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_list_view, parent, false);
        return new BookingAdapter.BookingViewHolder(view);
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

     holder.rateProperty.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(context, UserRatingActivity.class);
             Bundle bundle = new Bundle();
             bundle.putLong("bid", bookingResponse.getId());
             bundle.putLong("pid", bookingResponse.getPid());
             intent.putExtras(bundle);
             context.startActivity(intent);
         }
     });
     holder.raiseDispute.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, CreateDisputeActivity.class);
             Bundle bundle = new Bundle();
             bundle.putLong("bid", bookingResponse.getId());
             bundle.putLong("pid", bookingResponse.getPid());
             intent.putExtras(bundle);
             context.startActivity(intent);
         }
     });
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
         * Raise a Dispute
         */
        raiseDispute,
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
            propertyName = itemView.findViewById(R.id.textView_bookedPropertyName);
            startDate = itemView.findViewById(R.id.textView_start_date);
            endDate = itemView.findViewById(R.id.textView_end_date);
            rateProperty = itemView.findViewById(R.id.textView_rate_property);
            raiseDispute = itemView.findViewById(R.id.textView_raise_issue);
        }
    }
}
