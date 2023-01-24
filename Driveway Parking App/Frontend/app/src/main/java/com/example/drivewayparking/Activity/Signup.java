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
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.R;
import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Model.User;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Signup.
 * @author: Varun Advani
 */
public class Signup extends AppCompatActivity {

    /**
     * The Full name.
     */
    EditText fullName,
    /**
     * The Email.
     */
    email,
    /**
     * The Password.
     */
    password,
    /**
     * The Confirm password.
     */
    confirm_password,
    /**
     * The Phone number.
     */
    phoneNumber,
    /**
     * The First name.
     */
    first_name,
    /**
     * The Last name.
     */
    last_name;
    /**
     * The Signup button.
     */
    Button signup_button,
    /**
     * The Login question.
     */
    loginQuestion;
    /**
     * The Progress bar.
     */
    ProgressBar progressBar;
    /**
     * The Check host.
     */
    CheckBox checkHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        first_name = findViewById(R.id.firstName);
        last_name = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        signup_button = findViewById(R.id.signup_button);
        loginQuestion = findViewById(R.id.loginQuestion);
        progressBar = findViewById(R.id.progress_bar);
        confirm_password = findViewById(R.id.password_confirm);
        checkHost = findViewById(R.id.check_host);

        loginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String pass_confirm = confirm_password.getText().toString().trim();
                String firstName = first_name.getText().toString().trim();
                String lastName = last_name.getText().toString().trim();
                String phone = phoneNumber.getText().toString();
                boolean response = checkHost.isChecked();

                System.out.println(mail);

                if (TextUtils.isEmpty(mail)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    password.setError("Password is required");
                    return;
                }

                if (pass.length() < 6) {
                    password.setError("At least 6 characters");
                    return;
                }
                if(!(pass_confirm.equals(pass))){
                    confirm_password.setError("Password Does Not Match");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                User user = new User();
               // User user = new User(full_name, mail, pass, phone);

                user.setEmail(mail);
                System.out.println(user.getEmail());
                user.setPassword(pass);
                user.setPhoneNumber(phone);
                user.setFirstName(firstName);
                user.setLastName(lastName);
              //  user.setName(full_name);


                Call<Integer> call = ApiClient.getUserApiService().SignupUsers(user);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body() == 0){
                            progressBar.setVisibility(View.GONE);
                          //  Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Signup.this, LoginActivity.class);
                            intent.putExtra("name", firstName + " " + lastName);
                            intent.putExtra("email", mail);
                            intent.putExtra("password", pass);
                            intent.putExtra("phone", phone);
                            startActivity(intent);
                            System.out.println(user.getEmail());
                        }
                        else if(response.body() == 1){
                            Toast.makeText(Signup.this, "User already exists!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                      Toast.makeText(getApplicationContext(), "Failed " + t.getMessage() , Toast.LENGTH_SHORT).show();
                      System.out.println(t.getMessage());
                        progressBar.setVisibility(View.GONE);
                       // startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                });
              //  startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });
    }
}