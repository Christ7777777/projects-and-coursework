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
import com.example.drivewayparking.API.MockApiClient;
import com.example.drivewayparking.Adapter.DisputeAdapter;
import com.example.drivewayparking.Model.Admin;
import com.example.drivewayparking.Model.DisputeResponse;
import com.example.drivewayparking.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisputeFragment extends Fragment {

    private Long user_id;
    private static String admin_email;
    private RecyclerView recyclerView;
    private List<DisputeResponse> disputes;
    private DisputeAdapter disputeAdapter;

    public DisputeFragment() {
        // Required empty public constructor
    }

    public static DisputeFragment newInstance() {
        DisputeFragment fragment = new DisputeFragment();
        Bundle args = new Bundle();
        args.putString("admin_email", admin_email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            //user_id = getArguments().getLong("user_id");
            admin_email = getArguments().getString("admin_email");
            disputes = new ArrayList<>();
            //System.out.println(user_id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispute, container, false);

        recyclerView = view.findViewById(R.id.dispute_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<Admin> call_id = ApiClient.getAdminApiService().getAdminByEmail(admin_email);
        call_id.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if(response.isSuccessful()){
                    user_id = response.body().getId();
                    System.out.println("Admin id: " + user_id);
                    Call<List<DisputeResponse>> call_dispute = ApiClient.getDisputeApiService().getDisputesByAdmin(response.body().getId());
                    //Call<List<DisputeResponse>> call = MockApiClient.getDisputeApiService().getAllDisputes();

                    call_dispute.enqueue(new Callback<List<DisputeResponse>>() {
                        @Override
                        public void onResponse(Call<List<DisputeResponse>> call, Response<List<DisputeResponse>> response) {
                            if (response.isSuccessful()){
                                disputes = response.body();
                                System.out.println("check: " + disputes.size());
                                if (disputes != null){
                                    disputeAdapter = new DisputeAdapter(getActivity(), disputes);
                                    recyclerView.setAdapter(disputeAdapter);
                                }
                            }
                            else{
                                Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<DisputeResponse>> call, Throwable t) {
                            Toast.makeText(getActivity(), "Get disputes by admin error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(), "Code Admin ID 2: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        Call<List<DisputeResponse>> call = ApiClient.getDisputeApiService().getDisputesByAdmin(user_id);
//        //Call<List<DisputeResponse>> call = MockApiClient.getDisputeApiService().getAllDisputes();
//
//        call.enqueue(new Callback<List<DisputeResponse>>() {
//            @Override
//            public void onResponse(Call<List<DisputeResponse>> call, Response<List<DisputeResponse>> response) {
//                 if (response.isSuccessful()){
//                     disputes = response.body();
//                     System.out.println("check: " + disputes.size());
//                     if (disputes != null){
//                         disputeAdapter = new DisputeAdapter(getActivity(), disputes);
//                         recyclerView.setAdapter(disputeAdapter);
//                     }
//                 }
//                 else{
//                     Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                 }
//            }
//
//            @Override
//            public void onFailure(Call<List<DisputeResponse>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Get disputes by admin error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }


}