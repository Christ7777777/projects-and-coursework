package drivewayparking.app.enums;

public enum Status {
    OK(0),
    Error(1),
    Not_Found(2);

    private Integer value;
    private Status(Integer value) { this.value = value; }

    public int getValue() { return value; }
}
