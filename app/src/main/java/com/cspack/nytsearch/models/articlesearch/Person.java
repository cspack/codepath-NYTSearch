package com.cspack.nytsearch.models.articlesearch;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Person {

    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("lastname")
    @Expose
    private String lastname;

    /**
     * @return The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Person withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    /**
     * @return The role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    public Person withRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * @return The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Person withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * @return The rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank The rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Person withRank(Integer rank) {
        this.rank = rank;
        return this;
    }

    /**
     * @return The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Person withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

}