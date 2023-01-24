package com.example.drivewayparking.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Fragment.DisputeFragment;
import com.example.drivewayparking.Model.Admin;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;
import com.example.drivewayparking.Fragment.FindFragment;
import com.example.drivewayparking.Fragment.InboxFragment;
import com.example.drivewayparking.Fragment.ProfileFragment;
import com.example.drivewayparking.Fragment.ReservedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * The type Main activity admin.
 * @author: Varun Advani
 */
public class MainActivityAdmin extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * The Bottom navigation view.
     */
    BottomNavigationView bottomNavigationView;

    Bundle bundle;
    long admin_id;
    String email, admin_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_host);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bundle = new Bundle();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        email = getIntent().getStringExtra("f_email");
        admin_email = getIntent().getStringExtra("admin_email");
        Call<Admin> call = ApiClient.getAdminApiService().getAdminByEmail(admin_email);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if(response.isSuccessful()){
                    admin_id = response.body().getId();
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });

        System.out.println("email: " + email);
        bottomNavigationView.setSelectedItemId(R.id.find);
    }

    /**
     * The Park fragment.
     */
    FindFragment parkFragment = new FindFragment();
    DisputeFragment disputeFragment = new DisputeFragment();
    InboxFragment inboxFragment = new InboxFragment();
    /**
     * The Profile fragment.
     */
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.find:
                bundle.putString("f_email",email);
                parkFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, parkFragment).commit();
                //startActivity(new Intent(getApplicationContext(), CreateBookingActivity.class));
                return true;

            case R.id.reserved:
                //startActivity(new Intent(getApplicationContext(), CreatePropertyActivity.class));

//                Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        user_id = response.body().getId();
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        Toast.makeText(MainActivityAdmin.this, "Get user ID by email error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                bundle.putLong("user_id", user_id);
                bundle.putString("admin_email", admin_email);
                bundle.putLong("admin_id", admin_id);
                disputeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, disputeFragment).commit();
                return true;

            case R.id.inbox:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, inboxFragment).commit();
                return true;

            case R.id.profile:
                bundle.putString("admin_email",admin_email);
                profileFragment.setArguments(bundle);
                 getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                //Intent intent = new Intent(MainActivityAdmin.this, ProfileActivity.class);
                //intent.putExtra("f_name", getIntent().getStringExtra("f_name"));
//                intent.putExtra("f_email", getIntent().getStringExtra("f_email"));
//                //intent.putExtra("f_phone", getIntent().getStringExtra("f_phone"));
//                //intent.putExtra("f_password", getIntent().getStringExtra("f_password"));
//                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                return true;
        }
        return false;
    }

}