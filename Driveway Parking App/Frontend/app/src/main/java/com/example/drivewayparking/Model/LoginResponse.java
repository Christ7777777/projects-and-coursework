package com.example.drivewayparking.Model;

/**
 * The type Login response.
 */
public class LoginResponse {

    private Integer responseID;
    private Boolean isAdmin;

    /**
     * Instantiates a new Login response.
     */
    public LoginResponse(){

    }

    /**
     * Gets response id.
     *
     * @return the response id
     */
    public Integer getResponseID() {
        return responseID;
    }

    /**
     * Sets response id.
     *
     * @param responseID the response id
     */
    public void setResponseID(Integer responseID) {
        this.responseID = responseID;
    }

    /**
     * Gets admin.
     *
     * @return the admin
     */
    public Boolean getAdmin() {
        return isAdmin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}