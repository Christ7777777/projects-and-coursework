package com.example.drivewayparking.Model;

import java.time.LocalDate;
import java.util.Date;

/**
 * The type Booking.
 */
public class Booking {

    private Long uid;

    private Long pid;


    private int reservation_id;

    private String propertyName;

    private String check_in;

    private String check_out;

    private String startTime;

    private String endTime;

    private String zipcode;


    /**
     * Instantiates a new Booking.
     */
    public Booking(){}

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * Sets uid.
     *
     * @param uid the uid
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

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
     * Gets reservation id.
     *
     * @return the reservation id
     */
    public int getReservation_id() {
        return reservation_id;
    }

    /**
     * Sets reservation id.
     *
     * @param reservation_id the reservation id
     */
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    /**
     * Gets property name.
     *
     * @return the property name
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets property name.
     *
     * @param propertyName the property name
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
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
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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


    @Override
    public String toString() {
        return "Booking{" +
                "check_in=" + check_in +
                ", check_out=" + check_out +
                '}';
    }
}

