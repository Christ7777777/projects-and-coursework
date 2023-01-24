package com.example.drivewayparking.Model;

public class DisputeResponse {

    private Long id;
    private Long bookingId;
    private Long adminId;
    private String message;
    private boolean resolved;

    public DisputeResponse(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResolved() {
        return resolved;
    }

    @Override
    public String toString() {
        return "DisputeResponse{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", adminId=" + adminId +
                ", message='" + message + '\'' +
                ", resolved=" + resolved +
                '}';
    }
}
