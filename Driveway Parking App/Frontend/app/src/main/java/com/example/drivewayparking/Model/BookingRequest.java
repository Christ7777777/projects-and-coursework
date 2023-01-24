package com.example.drivewayparking.Model;

import java.sql.Timestamp;

/**
 * The type Booking request.
 */
public class BookingRequest {
    private Long uid;
    private Long pid;
    private String check_in;
    private String check_out;

    /**
     * Instantiates a new Booking request.
     */
    public BookingRequest(){

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

    @Override
    public String toString() {
        return "BookingRequest{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                '}';
    }
}
