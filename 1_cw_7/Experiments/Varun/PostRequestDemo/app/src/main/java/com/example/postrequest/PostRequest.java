package com.example.postrequest;

import com.google.gson.annotations.SerializedName;

public class PostRequest {
    @SerializedName("userId")
    private int userID;

    @SerializedName("id")
    private int ID;

    private String title;

    private String body;

    public int getUserID() {
        return userID;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


}
