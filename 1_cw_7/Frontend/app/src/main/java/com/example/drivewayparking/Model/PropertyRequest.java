package com.example.drivewayparking.Model;

import java.sql.Timestamp;
import java.util.List;

/**
 * The type Property request.
 */
public class PropertyRequest {

    private Long propertyId;
    private Long userId;

    private Double range;
    private Double latitude;
    private Double longitude;

    private String check_in;
    private String check_out;

    private String title;
    private String street;
    private String city;
    private String state;
    private Integer zipcode;
    private String country;

    private Double ratePerHour;

    /**
     * The Properties.
     */
    List<Property> properties;

    // Every new property needs to be approved by the admin
    private Boolean approved;

    // Type of parking
    private Boolean driveway;
    private Boolean garage;
    private Boolean parkingLot;
    private Boolean handicapped;

    // Vehicle type
    private Boolean car;
    private Boolean truck;
    private Boolean motorcycle;
    private Boolean oversized;

    // Amenities
    private Boolean EVcharging;
    private Boolean inOut;
    private Boolean tailgating;
    private Boolean shuttle;

    /**
     * Instantiates a new Property request.
     */
    public PropertyRequest() {
    }

    /**
     * Gets property id.
     *
     * @return the property id
     */
    public Long getPropertyId() {
        return propertyId;
    }

    /**
     * Sets property id.
     *
     * @param propertyId the property id
     */
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public Double getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(Double range) {
        this.range = range;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets check in.
     *
     * @return the check in
     */
    public String getCheck_in() {
        return check_in;
    }

    /**
     * Sets check in.
     *
     * @param check_in the check in
     */
    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    /**
     * Gets check out.
     *
     * @return the check out
     */
    public String getCheck_out() {
        return check_out;
    }

    /**
     * Sets check out.
     *
     * @param check_out the check out
     */
    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zipcode.
     *
     * @return the zipcode
     */
    public Integer getZipcode() {
        return zipcode;
    }

    /**
     * Sets zipcode.
     *
     * @param zipcode the zipcode
     */
    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets rate per hour.
     *
     * @return the rate per hour
     */
    public Double getRatePerHour() {
        return ratePerHour;
    }

    /**
     * Sets rate per hour.
     *
     * @param ratePerHour the rate per hour
     */
    public void setRatePerHour(Double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    /**
     * Gets properties.
     *
     * @return the properties
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * Sets properties.
     *
     * @param properties the properties
     */
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    /**
     * Gets approved.
     *
     * @return the approved
     */
    public Boolean getApproved() {
        return approved;
    }

    /**
     * Sets approved.
     *
     * @param approved the approved
     */
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * Gets driveway.
     *
     * @return the driveway
     */
    public Boolean getDriveway() {
        return driveway;
    }

    /**
     * Sets driveway.
     *
     * @param driveway the driveway
     */
    public void setDriveway(Boolean driveway) {
        this.driveway = driveway;
    }

    /**
     * Gets garage.
     *
     * @return the garage
     */
    public Boolean getGarage() {
        return garage;
    }

    /**
     * Sets garage.
     *
     * @param garage the garage
     */
    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    /**
     * Gets parking lot.
     *
     * @return the parking lot
     */
    public Boolean getParkingLot() {
        return parkingLot;
    }

    /**
     * Sets parking lot.
     *
     * @param parkingLot the parking lot
     */
    public void setParkingLot(Boolean parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * Gets handicapped.
     *
     * @return the handicapped
     */
    public Boolean getHandicapped() {
        return handicapped;
    }

    /**
     * Sets handicapped.
     *
     * @param handicapped the handicapped
     */
    public void setHandicapped(Boolean handicapped) {
        this.handicapped = handicapped;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Boolean getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Boolean car) {
        this.car = car;
    }

    /**
     * Gets truck.
     *
     * @return the truck
     */
    public Boolean getTruck() {
        return truck;
    }

    /**
     * Sets truck.
     *
     * @param truck the truck
     */
    public void setTruck(Boolean truck) {
        this.truck = truck;
    }

    /**
     * Gets motorcycle.
     *
     * @return the motorcycle
     */
    public Boolean getMotorcycle() {
        return motorcycle;
    }

    /**
     * Sets motorcycle.
     *
     * @param motorcycle the motorcycle
     */
    public void setMotorcycle(Boolean motorcycle) {
        this.motorcycle = motorcycle;
    }

    /**
     * Gets oversized.
     *
     * @return the oversized
     */
    public Boolean getOversized() {
        return oversized;
    }

    /**
     * Sets oversized.
     *
     * @param oversized the oversized
     */
    public void setOversized(Boolean oversized) {
        this.oversized = oversized;
    }

    /**
     * Gets e vcharging.
     *
     * @return the e vcharging
     */
    public Boolean getEVcharging() {
        return EVcharging;
    }

    /**
     * Sets e vcharging.
     *
     * @param EVcharging the e vcharging
     */
    public void setEVcharging(Boolean EVcharging) {
        this.EVcharging = EVcharging;
    }

    /**
     * Gets in out.
     *
     * @return the in out
     */
    public Boolean getInOut() {
        return inOut;
    }

    /**
     * Sets in out.
     *
     * @param inOut the in out
     */
    public void setInOut(Boolean inOut) {
        this.inOut = inOut;
    }

    /**
     * Gets tailgating.
     *
     * @return the tailgating
     */
    public Boolean getTailgating() {
        return tailgating;
    }

    /**
     * Sets tailgating.
     *
     * @param tailgating the tailgating
     */
    public void setTailgating(Boolean tailgating) {
        this.tailgating = tailgating;
    }

    /**
     * Gets shuttle.
     *
     * @return the shuttle
     */
    public Boolean getShuttle() {
        return shuttle;
    }

    /**
     * Sets shuttle.
     *
     * @param shuttle the shuttle
     */
    public void setShuttle(Boolean shuttle) {
        this.shuttle = shuttle;
    }

    @Override
    public String toString() {
        return "PropertyRequest{" +
                "propertyId=" + propertyId +
                ", userId=" + userId +
                ", range=" + range +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", check_in=" + check_in +
                ", check_out=" + check_out +
                ", title='" + title + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", country='" + country + '\'' +
                ", ratePerHour=" + ratePerHour +
                ", properties=" + properties +
                ", approved=" + approved +
                ", driveway=" + driveway +
                ", garage=" + garage +
                ", parkingLot=" + parkingLot +
                ", handicapped=" + handicapped +
                ", car=" + car +
                ", truck=" + truck +
                ", motorcycle=" + motorcycle +
                ", oversized=" + oversized +
                ", EVcharging=" + EVcharging +
                ", inOut=" + inOut +
                ", tailgating=" + tailgating +
                ", shuttle=" + shuttle +
                '}';
    }
}

