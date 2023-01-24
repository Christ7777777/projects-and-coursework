package com.example.drivewayparking.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Activity.ViewAllPropertiesActivity;
import com.example.drivewayparking.Activity.ViewAllPropertiesByDateActivity;
import com.example.drivewayparking.Model.Booking;
import com.example.drivewayparking.Model.Property;
import com.example.drivewayparking.Model.PropertyRequest;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.Model.UserRequest;
import com.example.drivewayparking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Find fragment.
 * @author: Varun Advani
 */
public class FindFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private Button toggleView, filterView;
    private SearchView searchView;
    private Bundle bundle;
    private double latitude;
    /**
     * The Email.
     */
    String email, admin_email;
    private double longitude;
    private List<String> title;
    /**
     * The Addresses.
     */
    List<Address> addresses;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Long user_id;

    private ArrayList<LatLng> locationArrayList;

    /**
     * Instantiates a new Find fragment.
     */
    public FindFragment() {
        // Required empty public constructor
    }

    /**
     * New instance find fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the find fragment
     */
    public static FindFragment newInstance(String param1, String param2) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * New inst find fragment.
     *
     * @param args the args
     * @return the find fragment
     */
    public static FindFragment newInst(Bundle args) {
        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            email = getArguments().getString("f_email");

            System.out.println(email);
        }

        //email = getArguments().getString("f_email");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.onCreate(savedInstanceState);
        mapFragment.getMapAsync(this);
        toggleView = view.findViewById(R.id.button_list_view);
        //searchView = view.findViewById(R.id.idSearchView_renter);
        filterView = view.findViewById(R.id.button_filter);
//        searchView.setFocusable(true);
//        searchView.setIconified(false);
        bundle = new Bundle();
//        if(user_id != null) {
//            bundle.putLong("id", user_id);
//            System.out.println(user_id);
//        }

        if(email != null) {
            Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            bundle.putLong("user_id", response.body().getId());
                            System.out.println(response.body().getId());
                        }
                    } else {
                        Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    System.out.println("Error: " + t.getMessage());
                }
            });
        }
        toggleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewAllPropertiesByDateActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

  filterView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.activity_create_booking);
        EditText start_date = dialog.findViewById(R.id.editTextListingStartDate);
        EditText end_date = dialog.findViewById(R.id.editTextListingEndDate);
        EditText start_time = dialog.findViewById(R.id.editTextListingStartTime);
        EditText end_time = dialog.findViewById(R.id.editTextListingEndTime);
        EditText zip_code = dialog.findViewById(R.id.editTextZipcode);
        EditText location = dialog.findViewById(R.id.editTextLocation);
        CheckBox isDriveway = dialog.findViewById(R.id.checkbox_driveway);
        CheckBox isCar = dialog.findViewById(R.id.checkbox_car);
        CheckBox isEV = dialog.findViewById(R.id.checkbox_ev);
        CheckBox isGarage = dialog.findViewById(R.id.checkbox_garage);
        CheckBox isTruck = dialog.findViewById(R.id.checkbox_truck);
        CheckBox isInout = dialog.findViewById(R.id.checkbox_inout);
        CheckBox isParkingLot = dialog.findViewById(R.id.checkbox_parkinglot);
        CheckBox isHandicapped = dialog.findViewById(R.id.checkbox_handicapped);
        CheckBox isOversize = dialog.findViewById(R.id.checkbox_oversized);
        CheckBox isMotorcycle = dialog.findViewById(R.id.checkbox_motorcycle);
        CheckBox isTailgating = dialog.findViewById(R.id.checkbox_tailgating);
        CheckBox isShuttle = dialog.findViewById(R.id.checkbox_shuttle);
        Button view_all_properties = dialog.findViewById(R.id.viewAllBookings);
        view_all_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Booking booking = new Booking();

                Intent intent = new Intent(getActivity(), ViewAllPropertiesByDateActivity.class);
                String start = start_date.getText().toString() + " " + start_time.getText().toString();
                if(TextUtils.isEmpty(start)){
                    start_date.setError("Please put in a start date");
                    start_time.setError("Please put in a start time");
                }
                String end = end_date.getText().toString() + " " + end_time.getText().toString();
                if(TextUtils.isEmpty(start)){
                    end_date.setError("Please put in a start date");
                    end_time.setError("Please put in a start time");
                }
                String zipcode = zip_code.getText().toString();
                String location_temp = location.getText().toString();
                if(TextUtils.isEmpty(location_temp)){
                    location.setError("Please put in location");
                }

                Boolean is_driveway = isDriveway.isChecked();
                Boolean is_car = isCar.isChecked();
                Boolean is_EV = isEV.isChecked();
                Boolean is_garage = isGarage.isChecked();
                Boolean is_truck = isTruck.isChecked();
                Boolean is_inout = isInout.isChecked();
                Boolean is_parkingLot = isParkingLot.isChecked();
                Boolean is_handicapped = isHandicapped.isChecked();
                Boolean is_oversize = isOversize.isChecked();
                Boolean is_motorcycle = isMotorcycle.isChecked();
                Boolean is_tailgating = isTailgating.isChecked();
                Boolean is_shuttle = isShuttle.isChecked();

                bundle.putBoolean("driveway", is_driveway);
                bundle.putBoolean("car", is_car);
                bundle.putBoolean("EV", is_EV);
                bundle.putBoolean("garage", is_garage);
                bundle.putBoolean("truck", is_truck);
                bundle.putBoolean("inout", is_inout);
                bundle.putBoolean("parkingLot", is_parkingLot);
                bundle.putBoolean("handicapped", is_handicapped);
                bundle.putBoolean("oversize", is_oversize);
                bundle.putBoolean("motorcycle", is_motorcycle);
                bundle.putBoolean("tailgating", is_tailgating);
                bundle.putBoolean("shuttle", is_shuttle);




                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocationName(location_temp + " " + zipcode, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(addresses.size() == 0){
                    Toast.makeText(getActivity(), "No Property found", Toast.LENGTH_SHORT).show();
                    return;
                }
                latitude = addresses.get(0).getLatitude();
                bundle.putDouble("latitude", latitude);
                System.out.println(addresses.get(0).getLatitude());
                longitude = addresses.get(0).getLongitude();
                bundle.putDouble("longitude", longitude);
                System.out.println(addresses.get(0).getLongitude());
                addresses.remove(0);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                output.setTimeZone(TimeZone.getTimeZone("CST"));

                try {
                    Date start_d = sdf.parse(start);
                    String format_one = output.format(start_d);
                    System.out.println(format_one);
                    booking.setCheck_in(format_one);
                    bundle.putString("start_date", format_one);
                }
                catch(ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Date end_d = sdf.parse(end);
                    String format_two = output.format(end_d);
                    System.out.println(format_two);
                    booking.setCheck_out(format_two);
                    bundle.putString("end_date", format_two);
                }
                catch(ParseException e) {
                    e.printStackTrace();
                }

                bundle.putString("f_zipcode", zipcode);


                dialog.dismiss();
                map.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10.0f));
//                intent.putExtras(bundle);
//                startActivity(intent);



                // Call<Integer> call = ApiClient.getBookingApiService().addBooking()
            }
        });
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        //dialog.getWindow().setAttributes(lp);

      //  startActivity(new Intent (getActivity(), CreateBookingActivity.class));
    }
});
        return view;

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMinZoomPreference(1.0f);
        map.setMaxZoomPreference(14.0f);
        //map.getUiSettings().setMyLocationButtonEnabled(false);
        //map.setMyLocationEnabled(true);
        Call<List<Property>> call = ApiClient.getPropertyApiService().getAllProperties();
        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(response.isSuccessful()){
                    List<Property> properties = response.body();
                    System.out.println(properties.size());
                    for (Property property : properties) {
                      map.addMarker(new MarkerOptions()
                              .position(new LatLng(property.getLatitude(), property.getLongitude()))
                              .title(property.getTitle())
                              .snippet("Hourly Rate: " + property.getRatePerHour()));
                      System.out.println(property.getTitle());
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                   Toast.makeText(getActivity(), "Error: " + t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
            float zoom = 3.0f;
            LatLng ISU = new LatLng(42.0267, -93.6465);
            //map.addMarker(new MarkerOptions().position(ISU).title("Default"));
            map.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ISU, zoom));

    }
    /*
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    */
}