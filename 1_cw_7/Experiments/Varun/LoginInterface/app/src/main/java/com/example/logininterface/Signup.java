package com.example.logininterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    EditText fullName, email, password, phoneNumber;
    Button signup_button, loginQuestion;
    ProgressBar progressBar;
    private static final String TAG = "TAG";

    FirebaseFirestore fireStore ;
    String userID;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        signup_button = findViewById(R.id.signup_button);
        loginQuestion = findViewById(R.id.loginQuestion);
        progressBar = findViewById(R.id.progress_bar);
        firebaseAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

//        if(firebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//           // finish();
//        }

        loginQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail = email.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                final String full_name = fullName.getText().toString();
                final String phone = phoneNumber.getText().toString();

                if(TextUtils.isEmpty(mail)) {
                    email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(pass)) {
                    password.setError("Password is required");
                    return;
                }

               if(pass.length() < 6) {
                   password.setError("At least 6 characters");
                   return;
               }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Sign up was successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Failure: Email not sent " + e.getMessage());
                                }
                            });
                            Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference reference = fireStore.collection("user").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Full Name ", full_name);
                            user.put("Email ", mail);
                            user.put("Phone number ", phone);
                            reference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "Success: User profile created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Failure: " + e);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        }
                        else{
                            Toast.makeText(Signup.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}