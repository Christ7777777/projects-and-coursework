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
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type View all properties activity.
 * @author: Varun Advani
 */
public class ViewAllPropertiesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Property> propertyList;
    private PropertyAdapter propertyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_properties);

        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Property>> call = ApiClient.getPropertyApiService().getAllProperties();
        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(response.isSuccessful()){
                    propertyList = response.body();
                    propertyAdapter = new PropertyAdapter(ViewAllPropertiesActivity.this, response.body());
                    recyclerView.setAdapter(propertyAdapter);
                  // recyclerView.setAdapter(new PropertyAdapter(ViewAllPropertiesActivity.this, response.body()));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
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
                Dialog dialog = new Dialog(ViewAllPropertiesActivity.this);
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
                                            //System.out.println(propertyRequest.toString());
                                        }
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                        //System.out.println(propertyRequest.toString());
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
        propertyAdapter.setFilter(filterPropertiesList);
        return false;
    }

    private List<Property> filter (List<Property> properties, String query){
        query = query.toLowerCase();

        final List<Property> filterPropertiesList = new ArrayList<>();
        for(Property property : properties){
//            final String search_text = property.getTitle().toLowerCase();
//            final String search_address = property.getAddress().toLowerCase();
            final String search_zipCode = property.getZipcode().toLowerCase();
            if(search_zipCode.contains(query)){
                filterPropertiesList.add(property);
            }
        }
        return filterPropertiesList;
    }
}