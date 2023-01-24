package com.example.experimentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.park);
    }

    Park parkFragment = new Park();
    Reserve reserveFragment = new Reserve();
    Inbox inboxFragment = new Inbox();
    Profile profileFragment = new Profile();

    public void gotoPage1(View view) {
        Intent intent = new Intent(this, DisplayPageActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.park:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, parkFragment).commit();
                return true;

            case R.id.reserve:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, reserveFragment).commit();
                return true;

            case R.id.inbox:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, inboxFragment).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                return true;
        }
        return false;
    }

}