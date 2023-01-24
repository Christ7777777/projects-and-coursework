package com.example.drivewayparking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.LoginRequest;
import com.example.drivewayparking.Model.LoginResponse;
import com.example.drivewayparking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * The type Login activity.
 * @author: Varun Advani
 */
public class LoginActivity extends AppCompatActivity {


    /**
     * The Username.
     */
    EditText username,
    /**
     * The L password.
     */
    l_password;
    /**
     * The Login button.
     */
    Button login_button,
    /**
     * The Sign up question button.
     */
    signUpQuestion_button;
    /**
     * The Progress bar.
     */
    ProgressBar progressBar;
    /**
     * The Check box.
     */
    CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.email_username);
        l_password = findViewById(R.id.password_login);
        login_button = findViewById(R.id.login_button);
        signUpQuestion_button = findViewById(R.id.signUpQuestion);
        progressBar = findViewById(R.id.progress_bar_login);
        checkBox = findViewById(R.id.checkbox_usertype);

        signUpQuestion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString().toLowerCase().trim();
                String pass = l_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    username.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    l_password.setError("Password is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = getIntent();
                String user_name = intent.getStringExtra("name");
                //System.out.println(user_name);
                String f_email = intent.getStringExtra("email");
                String f_phone = intent.getStringExtra("phone");

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setEmail(email);
                loginRequest.setPassword(pass);

//                UpdatePassword password = new UpdatePassword();
//                password.setPassword(pass);

                Call<LoginResponse> call = ApiClient.getUserApiService().getLogin(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        progressBar.setVisibility(View.GONE);
                       // System.out.println(response.body().getResponseID());
                        if(response.body().getResponseID() == 1){
                            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.body().getResponseID() == 0){
                            if(response.body().getAdmin()) {
                                Toast.makeText(getApplicationContext(), "Login Successful for Admin ", Toast.LENGTH_LONG).show();
                                Intent newIntent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                                //  newIntent.putExtra("f_name", response.body().getName());
                               // newIntent.putExtra("f_name", r_name);
                                newIntent.putExtra("admin_email", email);
                                // newIntent.putExtra("f_phone", response.body().getPhoneNumber());
                                //newIntent.putExtra("f_phone", r_phone);
                                //newIntent.putExtra("f_password", pass);
                                startActivity(newIntent);
                            }
                            else if(checkBox.isChecked()) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent newIntent = new Intent(LoginActivity.this, MainActivityHost.class);
                                //  newIntent.putExtra("f_name", response.body().getName());
                               // newIntent.putExtra("f_name", r_name);
                                newIntent.putExtra("f_email", email);
                                // newIntent.putExtra("f_phone", response.body().getPhoneNumber());
                               // newIntent.putExtra("f_phone", r_phone);
                                //newIntent.putExtra("f_password", pass);
                                startActivity(newIntent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent newIntent = new Intent(LoginActivity.this, MainActivityRenter.class);
                                //  newIntent.putExtra("f_name", response.body().getName());
                                // newIntent.putExtra("f_name", r_name);
                                newIntent.putExtra("f_email", email);
                                // newIntent.putExtra("f_phone", response.body().getPhoneNumber());
                                // newIntent.putExtra("f_phone", r_phone);
                                //newIntent.putExtra("f_password", pass);
                                startActivity(newIntent);
                            }
//                            if(response.body().getAccount_type() == 0) {
//                                Toast.makeText(getApplicationContext(), "Login Successful for Admin", Toast.LENGTH_SHORT).show();
//                                Intent newIntent = new Intent(LoginActivity.this, ProfileActivity.class);
//                                //  newIntent.putExtra("f_name", response.body().getName());
//                                newIntent.putExtra("f_name", r_name);
//                                newIntent.putExtra("f_email", email);
//                                // newIntent.putExtra("f_phone", response.body().getPhoneNumber());
//                                newIntent.putExtra("f_phone", r_phone);
//                                newIntent.putExtra("f_password", pass);
//                                startActivity(newIntent);
//                            }
//                           else if(response.body().getAccount_type() == 1) {
//                                Toast.makeText(getApplicationContext(), "Login Successful for Host", Toast.LENGTH_SHORT).show();
//                                Intent newIntent = new Intent(LoginActivity.this, ProfileActivity.class);
//                                //  newIntent.putExtra("f_name", response.body().getName());
//                                newIntent.putExtra("f_name", r_name);
//                                newIntent.putExtra("f_email", email);
//                                // newIntent.putExtra("f_phone", response.body().getPhoneNumber());
//                                newIntent.putExtra("f_phone", r_phone);
//                                newIntent.putExtra("f_password", pass);
//                                startActivity(newIntent);
//                            }
//                           else if(response.body().getAccount_type() == 2) {
//                                Toast.makeText(getApplicationContext(), "Login Successful for Renter", Toast.LENGTH_SHORT).show();
//                                Intent newIntent = new Intent(LoginActivity.this, ProfileActivity.class);
//                                //  newIntent.putExtra("f_name", response.body().getName());
//                                newIntent.putExtra("f_name", r_name);
//                                newIntent.putExtra("f_email", email);
//                                // newIntent.putExtra("f_phone", response.body().getPhoneNumber());
//                                newIntent.putExtra("f_phone", r_phone);
//                                newIntent.putExtra("f_password", pass);
//                                startActivity(newIntent);
//                            }
                        }
                        else if(response.body().getResponseID() == 2){
                            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(getApplicationContext(), "Failed  " +  t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}
