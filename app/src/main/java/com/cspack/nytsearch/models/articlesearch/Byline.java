package com.cspack.nytsearch.models.articlesearch;

import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Byline {

    @SerializedName("person")
    @Expose
    private List<Person> person = new ArrayList<Person>();
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("organization")
    @Expose
    private String organization;

    /**
     *
     * @return
     *     The person
     */
    public List<Person> getPerson() {
        return person;
    }

    /**
     *
     * @param person
     *     The person
     */
    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public Byline withPerson(List<Person> person) {
        this.person = person;
        return this;
    }

    /**
     * 
     * @return
     *     The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    public Byline withOriginal(String original) {
        this.original = original;
        return this;
    }

    /**
     * 
     * @return
     *     The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * 
     * @param organization
     *     The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Byline withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

}
