
package com.cspack.nytsearch.models.articlesearch;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Generated("org.jsonschema2pojo")
@Parcel
public class Meta {

    @SerializedName("hits")
    @Expose
    private Integer hits;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    /**
     * 
     * @return
     *     The hits
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * 
     * @param hits
     *     The hits
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Meta withHits(Integer hits) {
        this.hits = hits;
        return this;
    }

    /**
     * 
     * @return
     *     The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    public Meta withTime(Integer time) {
        this.time = time;
        return this;
    }

    /**
     * 
     * @return
     *     The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Meta withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

}
