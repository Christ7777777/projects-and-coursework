package com.example.drivewayparking.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

/**
 * The type Booking response.
 */
public class BookingResponse {
    private Long pid;
    private Long id;
    private Long uid;
    private Timestamp check_in;
    private Timestamp check_out;
    private double cost;
    private double hours;

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
     * Gets check in.
     *
     * @return the check in
     */
    public Timestamp getCheck_in() {
        return check_in;
    }

    /**
     * Sets check in.
     *
     * @param check_in the check in
     */
    public void setCheck_in(Timestamp check_in) {
        this.check_in = check_in;
    }

    /**
     * Gets check out.
     *
     * @return the check out
     */
    public Timestamp getCheck_out() {
        return check_out;
    }

    /**
     * Sets check out.
     *
     * @param check_out the check out
     */
    public void setCheck_out(Timestamp check_out) {
        this.check_out = check_out;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * Gets cost.
     *
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets hours.
     *
     * @return the hours
     */
    public double getHours() {
        return hours;
    }

    /**
     * Sets hours.
     *
     * @param hours the hours
     */
    public void setHours(double hours) {
        this.hours = hours;
    }
}
