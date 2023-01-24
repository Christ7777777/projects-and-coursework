package com.example.drivewayparking.Model;

public class DisputeRequest {

    private long bookingId;
    private String message;

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DisputeRequest{" +
                "bookingId=" + bookingId +
                ", message='" + message + '\'' +
                '}';
    }
}
