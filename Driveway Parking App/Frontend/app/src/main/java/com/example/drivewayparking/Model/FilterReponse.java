package com.example.drivewayparking.Model;

import java.util.logging.Filter;

/**
 * The type Filter reponse.
 */
public class FilterReponse {

    private String start_date;
    private String end_date;
    private String zipcode;
    private double latitude;
    private double longitude;
    private double radius;

    /**
     * Instantiates a new Filter reponse.
     */
    public FilterReponse(){

    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * Sets start date.
     *
     * @param start_date the start date
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     * Sets end date.
     *
     * @param end_date the end date
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "FilterReponse{" +
                "start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                '}';
    }
}
