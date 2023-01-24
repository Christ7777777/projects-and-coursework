package drivewayparking.app.enums;

public enum EntityType {
    User(0),
    Property(1),
    Dispute(2),
    Admin(3);

    private final Integer value;
    EntityType(Integer value) { this.value = value; }

    public int getValue() { return value; }
}
