package drivewayparking.app.enums;

public enum BookingStatus {

    OK(0),
    User_Not_Found(1),
    Property_Not_Found(2),
    Conflicting_Booking(3),
    Invalid_Time_Request(4),
    Booking_Not_Found(5);

    private Integer value;
    private BookingStatus(Integer value) { this.value = value; }

    public int getValue() { return value; }
}

