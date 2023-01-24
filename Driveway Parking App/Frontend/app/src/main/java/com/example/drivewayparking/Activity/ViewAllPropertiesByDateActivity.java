package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.PropertyAdapter;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.Model.PropertyRequest;
import com.example.drivewayparking.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type View all properties by date activity.
 * @author: Varun Advani
 */
public class ViewAllPropertiesByDateActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Property> propertyList;
    private PropertyAdapter propertyAdapter;
    private double latitude;
    private double longitude;
    private String start_date;
    private String end_date;
    private String zipcode;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_properties_by_date);

        searchView = findViewById(R.id.search_bar_date);
        //searchView.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.recyclerView_date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Bundle bundle = getIntent().getExtras();
         latitude = bundle.getDouble("latitude");
         longitude = bundle.getDouble("longitude");
         start_date = bundle.getString("start_date");
         end_date = bundle.getString("end_date");
         zipcode = bundle.getString("zipcode");
        Long user_id = bundle.getLong("user_id");
        String zipcode = bundle.getString("f_zipcode");



        System.out.println(latitude + " " + longitude);
        PropertyRequest propertyRequest = new PropertyRequest();
       // propertyRequest.setUserId(user_id);
        propertyRequest.setLatitude(latitude);
        propertyRequest.setLongitude(longitude);
        propertyRequest.setCheck_in(start_date);
        propertyRequest.setCheck_out(end_date);
        propertyRequest.setRange(100.0);
        propertyRequest.setDriveway(bundle.getBoolean("driveway"));
        propertyRequest.setCar(bundle.getBoolean("car"));
        propertyRequest.setEVcharging(bundle.getBoolean("EV"));
        propertyRequest.setGarage(bundle.getBoolean("garage"));
        propertyRequest.setTruck(bundle.getBoolean("truck"));
        propertyRequest.setInOut(bundle.getBoolean("inout"));
        propertyRequest.setParkingLot(bundle.getBoolean("parkingLot"));
        propertyRequest.setHandicapped(bundle.getBoolean("handicapped"));
        propertyRequest.setOversized(bundle.getBoolean("oversize"));
        propertyRequest.setMotorcycle(bundle.getBoolean("motorcycle"));
        propertyRequest.setTailgating(bundle.getBoolean("shuttle"));
        propertyRequest.setTailgating(bundle.getBoolean("tailgating"));


        propertyList = new ArrayList<>();

        Call<List<Property>> call = ApiClient.getPropertyApiService().getPropertiesByUserRequest(propertyRequest);
        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(response.isSuccessful()){
                    propertyList = response.body();
                    System.out.println(propertyList.toString() + "");
                    if(propertyList.size() != 0) {
                        propertyAdapter = new PropertyAdapter(ViewAllPropertiesByDateActivity.this, response.body(), start_date, end_date, user_id);
                        recyclerView.setAdapter(propertyAdapter);
                        System.out.println(user_id);
                        // recyclerView.setAdapter(new PropertyAdapter(ViewAllPropertiesActivity.this, response.body()));
                    }
                    else{
                        Toast.makeText(ViewAllPropertiesByDateActivity.this, "No properties found", Toast.LENGTH_SHORT).show();
                        System.out.println(propertyRequest);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    System.out.println(propertyRequest);
                }

            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ViewAllPropertiesByDateActivity.this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.filter_dialog);
                CheckBox sortByRatings = dialog.findViewById(R.id.sortByRatings);
                CheckBox sortByCheapest = dialog.findViewById(R.id.sortByCheapest);
                CheckBox sortByMostExpensive = dialog.findViewById(R.id.sortByMostExpensive);
                Button sortButton = dialog.findViewById(R.id.sortButton);
                sortButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(sortByRatings.isChecked()){
                            Call<List<Property>> call = ApiClient.getPropertyApiService().sortPropertiesByRating(propertyList);
                            call.enqueue(new Callback<List<Property>>() {
                                @Override
                                public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                                    if(response.isSuccessful()){
                                        if(response.body().size() != 0){
                                            propertyList = response.body();
                                            propertyAdapter.notifyDataSetChanged();
//                                            propertyAdapter = new PropertyAdapter(ViewAllPropertiesByDateActivity.this, response.body(), start_date, end_date, user_id);
//                                            recyclerView.setAdapter(propertyAdapter);
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                            System.out.println(propertyRequest);
                                        }
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                        System.out.println(propertyRequest);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Property>> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        if(sortByCheapest.isChecked()) {
                            Collections.sort(propertyList, Property.sortByCheapest);
                            propertyAdapter.notifyDataSetChanged();
                        }
                        if(sortByMostExpensive.isChecked()) {
                            Collections.sort(propertyList, Property.mostExpensive);
                            propertyAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        final List<Property> filterPropertiesList = filter(propertyList, s);
        if(filterPropertiesList != null) {
            // propertyAdapter.setFilter(filterPropertiesList);
        }
        return false;
    }

    private List<Property> filter (List<Property> properties, String query){
        query = query.toLowerCase();

        final List<Property> filterPropertiesList = new ArrayList<>();

        for(Property property : properties){
//            final String search_text = property.getTitle().toLowerCase();
//            final String search_address = property.getAddress().toLowerCase();
            final String search_zipCode = property.getTitle().toLowerCase();
            if(search_zipCode.contains(query)){
                filterPropertiesList.add(property);
            }
        }
//        Call<List<Property>> call = ApiClient.getPropertyApiService().filterByZipcode(query);
//        call.enqueue(new Callback<List<Property>>() {
//            @Override
//            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
//                if(response.isSuccessful()){
//                    if(response.body() != null) {
//                        filterPropertiesList.addAll(response.body());
//                    }
//                    else{
//                        Toast.makeText(getApplicationContext(), "No Property Available", Toast.LENGTH_LONG).show();
//                    }
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Property>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
        return filterPropertiesList;
    }
}