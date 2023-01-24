package com.example.drivewayparking.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Activity.ViewSpecificPropertyActivity;
import com.example.drivewayparking.Model.Booking;
import com.example.drivewayparking.Model.BookingRequest;
import com.example.drivewayparking.Model.BookingResponse;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Property adapter.
 * @author: Varun Advani
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {
private final Context context;
private List<Property> propertyList;
private String start;
private String end;
private Long user_id;
private double cost;
private double hours;


    /**
     * Instantiates a new Property adapter.
     *
     * @param context      the context
     * @param propertyList the property list
     */
    public PropertyAdapter(Context context, List<Property> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    /**
     * Instantiates a new Property adapter.
     *
     * @param context      the context
     * @param propertyList the property list
     * @param start        the start
     * @param end          the end
     * @param user_id      the user id
     */
    public PropertyAdapter(Context context, List<Property> propertyList, String start, String end, Long user_id) {
        this.context = context;
        this.propertyList = propertyList;
        this.start = start;
        this.end = end;
        this.user_id = user_id;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.property_list_view, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, final int position) {
       final Property list = propertyList.get(position);
       //Long propertyId = list.getPid();
       String street = list.getStreet();
       String city = list.getCity();
       String zipcode = list.getZipcode();
       Double rate = list.getRatePerHour();
       if(street == null || city == null || zipcode == null) {
           street = "";
           city = "";
           zipcode = "";
       }
       holder.propertyName.setText(list.getTitle());
       holder.propertyAddress.setText(street + " " + city + " " + zipcode);
       if(rate != null) {
           holder.propertyRate.setText("" + rate);
       }
       else{
           holder.propertyRate.setText("N/A");
       }



       holder.bookProperty.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               BookingRequest booking_request = new BookingRequest();
//               booking_request.setCheck_in(start);
//               booking_request.setCheck_out(end);
//               booking_request.setUid(user_id);
//               booking_request.setPid(list.getPid());
//               Call<BookingResponse> call = ApiClient.getBookingApiService().bookingRequest(booking_request);
//               call.enqueue(new Callback<BookingResponse>() {
//                   @Override
//                   public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
//                       if(response.isSuccessful()){
//                           if(response.body()!= null) {
//                               cost = response.body().getCost();
//                               System.out.println("Cost: " + cost);
//                               hours = response.body().getHours();
//                               System.out.println("Hours: " + hours);
//                           }
//                       }
//                       else{
//                           Toast.makeText(context.getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                       }
//                   }
//
//                   @Override
//                   public void onFailure(Call<BookingResponse> call, Throwable t) {
//                        Toast.makeText(context.getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                   }
//               });
               Dialog dialog = new Dialog(context);
               dialog.setCancelable(true);
               dialog.setContentView(R.layout.confirm_booking);
               Button confirm_booking = dialog.findViewById(R.id.confirmBooking);
               TextView costView = dialog.findViewById(R.id.textView_cost);
               TextView hourView = dialog.findViewById(R.id.textView_hours);
               System.out.println(cost);
               System.out.println(hours);
               BookingRequest booking_request = new BookingRequest();
               booking_request.setCheck_in(start);
               booking_request.setCheck_out(end);
               booking_request.setUid(user_id);
               booking_request.setPid(list.getPid());
               Call<BookingResponse> call = ApiClient.getBookingApiService().bookingRequest(booking_request);
               call.enqueue(new Callback<BookingResponse>() {
                   @Override
                   public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                       if(response.isSuccessful()){
                           if(response.body()!= null) {
                              costView.setText(Double.toString(response.body().getCost()));
                              hourView.setText(Double.toString(response.body().getHours()));
                               System.out.println("Cost: " + cost);
                               System.out.println("Hours: " + hours);
                           }
                       }
                       else{
                           Toast.makeText(context.getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<BookingResponse> call, Throwable t) {
                       Toast.makeText(context.getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });
               confirm_booking.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
               BookingRequest booking = new BookingRequest();
               booking.setCheck_in(start);
               booking.setCheck_out(end);
               booking.setUid(user_id);
               booking.setPid(list.getPid());
               System.out.println(booking);


               Call<Integer> call = ApiClient.getBookingApiService().addBooking(booking);
               call.enqueue(new Callback<Integer>() {
                   @Override
                   public void onResponse(Call<Integer> call, Response<Integer> response) {
                       if(response.isSuccessful()) {
                           if (response.body() == 0) {
                               dialog.dismiss();
                               Toast.makeText(context.getApplicationContext(), "Booking Created Successfully", Toast.LENGTH_SHORT).show();
                           }
                           else if(response.body() == 3) {
                               dialog.dismiss();
                               Toast.makeText(context.getApplicationContext(), "Property Not Available at given date and time", Toast.LENGTH_SHORT).show();
                           }

                       }
                       else{
                           dialog.dismiss();
                           Toast.makeText(context.getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<Integer> call, Throwable t) {
                       dialog.dismiss();
                        Toast.makeText(context.getApplicationContext(), "Error:" + t.getMessage(), Toast.LENGTH_LONG).show();
                   }
               });

           }
       });
               dialog.show();
           }
       });
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)  {
               Intent intent = new Intent(context, ViewSpecificPropertyActivity.class);
               Bundle bundle = new Bundle();
               bundle.putLong("pid", list.getPid());
               intent.putExtras(bundle);
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    /**
     * The type Property view holder.
     */
    static class PropertyViewHolder extends RecyclerView.ViewHolder{
        /**
         * The Property name.
         */
        TextView propertyName,
        /**
         * The Property address.
         */
        propertyAddress,
        /**
         * The Property rating.
         */
        propertyRating,
        /**
         * The Delete property.
         */
        deleteProperty,
        /**
         * The Property rate.
         */
        propertyRate,
        /**
         * The Book property.
         */
        bookProperty;
        /**
         * The Layout.
         */
        LinearLayout layout;


        /**
         * Instantiates a new Property view holder.
         *
         * @param itemView the item view
         */
        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.textView_propertyName);
            propertyAddress = itemView.findViewById(R.id.textView_address);
            propertyRate = itemView.findViewById(R.id.textView_rate);
            bookProperty = itemView.findViewById(R.id.textView_book_property);
            layout = itemView.findViewById(R.id.linearLayout_propertyList);

        }
    }

    /**
     * Set filter.
     *
     * @param dataModels the data models
     */
    public void setFilter(List<Property> dataModels){
        propertyList = new ArrayList<>();
        propertyList.addAll(dataModels);
        notifyDataSetChanged();
    }
}
