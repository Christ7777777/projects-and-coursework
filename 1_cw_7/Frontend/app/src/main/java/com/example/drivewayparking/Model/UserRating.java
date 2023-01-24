package com.example.drivewayparking.Model;

/**
 * The type User rating.
 */
public class UserRating {

    private int reservation_id;

    private float accommodation;

    private float safety;

    private float responsiveness;

    private String comments;


    /**
     * Instantiates a new User rating.
     */
    public UserRating(){}

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
     * Gets accommodation.
     *
     * @return the accommodation
     */
    public float getAccommodation() {
        return accommodation;
    }

    /**
     * Sets accommodation.
     *
     * @param accommodation the accommodation
     */
    public void setAccommodation(float accommodation) {
        this.accommodation = accommodation;
    }

    /**
     * Gets safety.
     *
     * @return the safety
     */
    public float getSafety() {
        return safety;
    }

    /**
     * Sets safety.
     *
     * @param safety the safety
     */
    public void setSafety(float safety) {
        this.safety = safety;
    }

    /**
     * Gets responsiveness.
     *
     * @return the responsiveness
     */
    public float getResponsiveness() {
        return responsiveness;
    }

    /**
     * Sets responsiveness.
     *
     * @param responsiveness the responsiveness
     */
    public void setResponsiveness(float responsiveness) {
        this.responsiveness = responsiveness;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

}
