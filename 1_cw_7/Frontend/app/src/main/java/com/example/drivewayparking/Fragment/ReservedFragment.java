package com.example.drivewayparking.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;
import com.google.android.material.tabs.TabLayout;

import org.checkerframework.checker.units.qual.C;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Reserved fragment.
 * @author: Varun Advani
 */
public class ReservedFragment extends Fragment{

    /**
     * The Tab layout.
     */
    TabLayout tabLayout;
    /**
     * The Fragment.
     */
    Fragment fragment = null;
    private String email;
    private Long user_id;
    private Bundle bundle;

    /**
     * Instantiates a new Reserved fragment.
     */
    public ReservedFragment() {
        // Required empty public constructor
    }

    /**
     * New instance reserved fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the reserved fragment
     */
    public static ReservedFragment newInstance(String param1, String param2) {
        ReservedFragment fragment = new ReservedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
           email = getArguments().getString("f_email");
           bundle = new Bundle();
           System.out.println(email);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserved, container, false);
        Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        user_id = response.body().getId();
                        bundle.putLong("user_id", user_id);
                        System.out.println(response.body().getId());
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                  Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout = view.findViewById(R.id.reservedTab);

        fragment = new ReservedUpcomingFragment();
        fragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.reservedSubFragment, fragment).commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0:
                        fragment = new ReservedUpcomingFragment();
                        break;
                    case 1:
                        fragment = new ReservedCompletedFragment();
                        break;
                }
                fragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().replace(R.id.reservedSubFragment, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}