package com.example.drivewayparking.Model;

import java.sql.Timestamp;

public class MessageResponse {

    private String message;
    private String name;
    private String bitmap;
    private long otherId;
    private Timestamp sentTime;
    private boolean isSent;

    public MessageResponse(){

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOtherId() {
        return otherId;
    }

    public void setOtherId(long otherId) {
        this.otherId = otherId;
    }

    public Timestamp getSent() {
        return sentTime;
    }

    public void setSent(Timestamp sent) {
        this.sentTime = sent;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }
}
