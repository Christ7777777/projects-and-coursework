package drivewayparking.app.dto;

import drivewayparking.app.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {

    private Long propertyId;
    private Long userId;

    private Double range;
    private Double latitude;
    private Double longitude;

    private Timestamp check_in;
    private Timestamp check_out;

    private String title;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
    private String country;

    private String description;
    private Double ratePerHour;

    private List<Property> properties;

    // Every new property needs to be approved by the admin
    private Boolean approved = true;

    // parking type
    private Boolean driveway = false;
    private Boolean garage = false;
    private Boolean parkingLot = false;
    private Boolean handicapped = false;

    // vehicle type
    private Boolean car = false;
    private Boolean truck = false;
    private Boolean motorcycle = false;
    private Boolean oversized = false;

    // amenities
    private Boolean EVcharging = false;
    private Boolean inOut = false;
    private Boolean tailgating = false;
    private Boolean shuttle = false;

}
