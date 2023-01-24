package com.example.drivewayparking.Model;

/**
 * The type Rating request.
 */
public class RatingRequest {
    private Long id;
    private Float accommodation;
    private Float safety;
    private Float responsiveness;
    private String comments;

    /**
     * Instantiates a new Rating request.
     */
    public RatingRequest() {
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
