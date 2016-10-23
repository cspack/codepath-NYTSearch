
package com.cspack.nytsearch.models.articlesearch;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Generated("org.jsonschema2pojo")
@Parcel
public class ArticleSearch {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    /**
     * 
     * @return
     *     The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     *     The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    public ArticleSearch withResponse(Response response) {
        this.response = response;
        return this;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public ArticleSearch withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * @return
     *     The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 
     * @param copyright
     *     The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArticleSearch withCopyright(String copyright) {
        this.copyright = copyright;
        return this;
    }

}
