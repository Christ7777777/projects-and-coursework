package com.example.logininterface;

import com.google.gson.annotations.SerializedName;

public class User {


    @SerializedName("Full name")
    public String fullName;


    public String email;

    public String password;


    @SerializedName("Phone Number")
    public String phone_number;

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
