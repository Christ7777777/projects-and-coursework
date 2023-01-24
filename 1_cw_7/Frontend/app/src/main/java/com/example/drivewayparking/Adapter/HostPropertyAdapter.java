package com.example.drivewayparking.Adapter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Activity.ViewAllBookingsActivity;
import com.example.drivewayparking.Activity.ViewAllUserPropertyActivity;
import com.example.drivewayparking.Activity.ViewSpecificPropertyActivity;
import com.example.drivewayparking.Model.Booking;
import com.example.drivewayparking.Model.BookingRequest;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Host property adapter.
 * @author: Varun Advani
 */
public class HostPropertyAdapter extends RecyclerView.Adapter<HostPropertyAdapter.PropertyViewHolder> {
    private final Context context;
    private List<Property> propertyList;
    private String start;
    private String end;
    private Long user_id;
    private final int IMAGE_REQUEST_ID = 1;


    /**
     * Instantiates a new Host property adapter.
     *
     * @param context      the context
     * @param propertyList the property list
     */
    public HostPropertyAdapter(Context context, List<Property> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    /**
     * Instantiates a new Host property adapter.
     *
     * @param context      the context
     * @param propertyList the property list
     * @param start        the start
     * @param end          the end
     * @param user_id      the user id
     */
    public HostPropertyAdapter(Context context, List<Property> propertyList, String start, String end, Long user_id) {
        this.context = context;
        this.propertyList = propertyList;
        this.start = start;
        this.end = end;
        this.user_id = user_id;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.property_list_view_host, parent, false);
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
             Intent intent = new Intent(context.getApplicationContext(), ViewAllBookingsActivity.class);
             Bundle bundle = new Bundle();
             bundle.putLong("pid", list.getPid());
             intent.putExtras(bundle);
             context.startActivity(intent);
            }
        });

        holder.uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setClass(context, ViewAllUserPropertyActivity.class);
                Bundle bundle = new Bundle();
                intent.setType("image/*");
                intent.putExtra("pid", list.getPid());
                ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Pick image"),
                        IMAGE_REQUEST_ID);


            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)  {
//                Intent intent = new Intent(context, ViewSpecificPropertyActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putLong("pid", list.getPid());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
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
        bookProperty,
        /**
         * Upload property Images
         */
        uploadImages;
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
            propertyName = itemView.findViewById(R.id.textView_propertyName_host);
            propertyAddress = itemView.findViewById(R.id.textView_address_host);
            propertyRate = itemView.findViewById(R.id.textView_rate_host);
            bookProperty = itemView.findViewById(R.id.textView_view_all_booked_property);
            uploadImages = itemView.findViewById(R.id.upload_images_property);
            //layout = itemView.findViewById(R.id.linearLayout_propertyList);

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
