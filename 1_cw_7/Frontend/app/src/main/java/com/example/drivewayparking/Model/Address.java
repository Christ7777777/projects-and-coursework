package com.example.drivewayparking.Model;

/**
 * The type Address.
 */
public class Address {

    private String street;
    private String city;
    private String   postal_code;

    /**
     * Instantiates a new Address.
     */
    public Address(){}

    public String toString(){
        return street + " " + city + " " + postal_code;
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
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * Sets postal code.
     *
     * @param postal_code the postal code
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
