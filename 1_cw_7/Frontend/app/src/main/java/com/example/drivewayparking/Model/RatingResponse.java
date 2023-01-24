package com.example.drivewayparking.Model;

/**
 * The type Rating response.
 */
public class RatingResponse {
    private Long rid;
    private Long uid;
    private Long pid;
    private Float accommodation;
    private Float safety;
    private Float responsiveness;
    private String comments;

    /**
     * Instantiates a new Rating response.
     */
    public RatingResponse(){

    }

    /**
     * Gets rid.
     *
     * @return the rid
     */
    public Long getRid() {
        return rid;
    }

    /**
     * Sets rid.
     *
     * @param rid the rid
     */
    public void setRid(Long rid) {
        this.rid = rid;
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
     * Gets accommodation.
     *
     * @return the accommodation
     */
    public Float getAccommodation() {
        return accommodation;
    }

    /**
     * Sets accommodation.
     *
     * @param accommodation the accommodation
     */
    public void setAccommodation(Float accommodation) {
        this.accommodation = accommodation;
    }

    /**
     * Gets safety.
     *
     * @return the safety
     */
    public Float getSafety() {
        return safety;
    }

    /**
     * Sets safety.
     *
     * @param safety the safety
     */
    public void setSafety(Float safety) {
        this.safety = safety;
    }

    /**
     * Gets responsiveness.
     *
     * @return the responsiveness
     */
    public Float getResponsiveness() {
        return responsiveness;
    }

    /**
     * Sets responsiveness.
     *
     * @param responsiveness the responsiveness
     */
    public void setResponsiveness(Float responsiveness) {
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
