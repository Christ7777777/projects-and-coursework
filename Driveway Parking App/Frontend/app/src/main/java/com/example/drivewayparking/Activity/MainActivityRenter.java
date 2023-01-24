package com.example.drivewayparking.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
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
 * The type Main activity renter.
 * @author: Varun Advani
 */
public class MainActivityRenter extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * The Bottom navigation view.
     */
    BottomNavigationView bottomNavigationView;
    private String email;
    private Long user_id;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_host);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
       // bottomNavigationView.setSelectedItemId(R.id.reserved);
        bundle = new Bundle();
        email = getIntent().getStringExtra("f_email");
        Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user_id = response.body().getId();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.find);
    }

    /**
     * The Reserve fragment.
     */
//    FindFragment parkFragment = new FindFragment();
    ReservedFragment reserveFragment = new ReservedFragment();
    /**
     * The Inbox fragment.
     */
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
                System.out.println(email);
                FindFragment parkFragment = FindFragment.newInst(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, parkFragment).commit();
                //startActivity(new Intent(getApplicationContext(), CreateBookingActivity.class));
                return true;

            case R.id.reserved:
                //startActivity(new Intent(getApplicationContext(), CreatePropertyActivity.class));
                 bundle.putString("f_email", email);
                 reserveFragment.setArguments(bundle);
                 getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, reserveFragment).commit();

                return true;

            case R.id.inbox:
                Intent intent_chat = new Intent(MainActivityRenter.this, MessageRoomActivity.class);
                bundle.putString("f_email", email);
                intent_chat.putExtras(bundle);
                startActivity(intent_chat);
                return true;

            case R.id.profile:


                bundle.putString("f_email", email);
                bundle.putLong("f_user_id", user_id);
                System.out.println(getIntent().getStringExtra("f_email"));
                ProfileFragment profileFragment = new ProfileFragment();
                //ProfileFragment.newInstance("f_email", getIntent().getStringExtra("f_email"));
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                Intent intent = new Intent(MainActivityHost.this, ProfileActivity.class);
//                //intent.putExtra("f_name", getIntent().getStringExtra("f_name"));
//                intent.putExtra("f_email", getIntent().getStringExtra("f_email"));
//               // intent.putExtra("f_phone", getIntent().getStringExtra("f_phone"));
//               // intent.putExtra("f_password", getIntent().getStringExtra("f_password"));
//                startActivity(intent);
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                return true;
        }
        return false;
    }

}