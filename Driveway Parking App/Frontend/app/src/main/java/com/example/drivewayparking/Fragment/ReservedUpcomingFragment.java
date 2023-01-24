package com.example.drivewayparking.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Adapter.BookingAdapter;
import com.example.drivewayparking.Adapter.BookingUpcomingAdapter;
import com.example.drivewayparking.Model.BookingResponse;
import com.example.drivewayparking.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Reserved upcoming fragment.
 * @author: Varun Advani
 */
public class ReservedUpcomingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Long user_id;
    private RecyclerView recyclerView;
    private List<BookingResponse> bookings;
    private BookingUpcomingAdapter bookingAdapter;


    /**
     * Instantiates a new Reserved upcoming fragment.
     */
    public ReservedUpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * New instance reserved upcoming fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the reserved upcoming fragment
     */
// TODO: Rename and change types and number of parameters
    public static ReservedUpcomingFragment newInstance(String param1, String param2) {
        ReservedUpcomingFragment fragment = new ReservedUpcomingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            user_id  = getArguments().getLong("user_id");
            bookings = new ArrayList<BookingResponse>();
            System.out.println(user_id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserved_upcoming, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_bookings_upcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Inflate the layout for this fragment
        Call<List<BookingResponse>> call = ApiClient.getBookingApiService().getBookingByUser(user_id);
        System.out.println(user_id + "");
        call.enqueue(new Callback<List<BookingResponse>>() {
            @Override
            public void onResponse(Call<List<BookingResponse>> call, Response<List<BookingResponse>> response) {
                if(response.isSuccessful()){
                    for(BookingResponse booking : response.body()){
                        if(new Date().before(booking.getCheck_out())){
                            bookings.add(booking);
                        }
                    }
                    //bookings = response.body();
                    if(bookings != null) {//Toast.makeText(getActivity(), "No upcoming bookings", Toast.LENGTH_SHORT).show();
                        bookingAdapter = new BookingUpcomingAdapter(getActivity(), bookings);
                        recyclerView.setAdapter(bookingAdapter);
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookingResponse>> call, Throwable t) {
               Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

//    @Override
//    public void onStart() {
//        // Inflate the layout for this fragment
//        super.onStart();
//        Call<List<BookingResponse>> call = ApiClient.getBookingApiService().getBookingByUser(user_id);
//        System.out.println(user_id + "");
//        call.enqueue(new Callback<List<BookingResponse>>() {
//            @Override
//            public void onResponse(Call<List<BookingResponse>> call, Response<List<BookingResponse>> response) {
//                if (response.isSuccessful()) {
//                    bookings = response.body();
//                    if (bookings.size() == 0) {
//                        Toast.makeText(getActivity(), "No upcoming bookings", Toast.LENGTH_SHORT).show();
//                    }
//                    bookingAdapter = new BookingAdapter(getActivity(), bookings);
//                    recyclerView.setAdapter(bookingAdapter);
//                } else {
//                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<BookingResponse>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    @Override
//    public void onResume() {
//        // Inflate the layout for this fragment
//        super.onResume();
//        Call<List<BookingResponse>> call = ApiClient.getBookingApiService().getBookingByUser(user_id);
//        System.out.println(user_id + "");
//        call.enqueue(new Callback<List<BookingResponse>>() {
//            @Override
//            public void onResponse(Call<List<BookingResponse>> call, Response<List<BookingResponse>> response) {
//                if (response.isSuccessful()) {
//                    bookings = response.body();
//                    if (bookings.size() == 0) {
//                        Toast.makeText(getActivity(), "No upcoming bookings", Toast.LENGTH_SHORT).show();
//                    }
//                    bookingAdapter = new BookingAdapter(getActivity(), bookings);
//                    recyclerView.setAdapter(bookingAdapter);
//                } else {
//                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<BookingResponse>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        // Inflate the layout for this fragment
//    }
    // Inflate the layout for this fragment
}