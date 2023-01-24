package com.example.drivewayparking.Model;

/**
 * The type Login request.
 */
public class LoginRequest {
    /**
     * The Email.
     */
    String email;
    /**
     * The Password.
     */
    String password;

    /**
     * Instantiates a new Login request.
     */
    public LoginRequest(){

    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
