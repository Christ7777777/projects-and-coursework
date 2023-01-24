package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.HostPropertyAdapter;
import com.example.drivewayparking.Adapter.PropertyAdapter;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.Model.PropertyRequest;
import com.example.drivewayparking.R;
import androidx.appcompat.widget.SearchView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type View all user property activity.
 * @author: Varun Advani
 */
public class ViewAllUserPropertyActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Property> propertyList;
    private HostPropertyAdapter propertyAdapter;
    private final int IMAGE_REQUEST_ID = 1;
    private Bitmap bmp;
    private Long pid;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {


            try {
                InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                byte[] bytes;
                File file = new File(getApplicationContext().getCacheDir(), "image_property_" + 6 + ".jpeg");
                bmp = BitmapFactory.decodeStream(inputStream);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
                bytes = output.toByteArray();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"),file);
                MultipartBody.Part image_upload = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                RequestBody dummy = RequestBody.create(MediaType.parse("multipart/form-data"), "");
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "6");
                Call<Integer> call = ApiClient.getImageApiService().uploadImageProperty(image_upload, requestBody);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()){
                            if(response.body() == 0){
                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Image Not Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_properties);

        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println(getIntent().getExtras().getLong("user_id"));
        //pid = getIntent().getExtras().getLong("pid");

        Call<List<Property>> call = ApiClient.getPropertyApiService().getPropertyByUserId(getIntent().getExtras().getLong("user_id"));
        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(response.isSuccessful()){
                    propertyList = response.body();
                    propertyAdapter = new HostPropertyAdapter(ViewAllUserPropertyActivity.this, response.body());
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
                System.out.println("Error: " + t.getMessage());
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