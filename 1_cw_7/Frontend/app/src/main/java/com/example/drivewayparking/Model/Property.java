package com.example.drivewayparking.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

/**
 * The type Property.
 */
public class Property {

    @SerializedName("id")
    private Long pid;
    private String title;
    private String street;
    private String zipcode;
    private String city;
    private String state;
    private String country;
    private Double ratePerHour;
    private double latitude;
    private double longitude;
    private String property_type;
    // Every new property needs to be approved by the admin
    private Boolean approved = false;

    // Type of parking
    private Boolean driveway = false;
    private Boolean garage = false;
    private Boolean parkingLot = false;
    private Boolean handicapped = false;

    // Vehicle type
    private Boolean car = false;
    private Boolean truck = false;
    private Boolean motorcycle = false;
    private Boolean oversized = false;

    // Amenities
    private Boolean EVcharging = false;
    private Boolean inOut = false;
    private Boolean tailgating = false;
    private Boolean shuttle = false;


    /**
     * Instantiates a new Property.
     */
    public Property(){}

    /**
     * Gets pid.
     *
     * @return the pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * Sets pid.
     *
     * @param pid the pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
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
     * Gets property type.
     *
     * @return the property type
     */
    public String getProperty_type() {
        return property_type;
    }

    /**
     * Sets property type.
     *
     * @param property_type the property type
     */
    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    /**
     * Gets zipcode.
     *
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets zipcode.
     *
     * @param zipcode the zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
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

    /**
     * The constant sortByCheapest.
     */
    public static Comparator<Property> sortByCheapest = new Comparator<Property>() {
        @Override
        public int compare(Property o1, Property o2) {
            return o1.getRatePerHour().compareTo(o2.getRatePerHour());
        }

    };

    /**
     * The constant mostExpensive.
     */
    public static Comparator<Property> mostExpensive = new Comparator<Property>() {
        @Override
        public int compare(Property o1, Property o2) {
            return o2.getRatePerHour().compareTo(o1.getRatePerHour());
        }

    };
}
