package com.cspack.nytsearch.models;

import org.parceler.Parcel;

import java.util.Date;
import java.util.List;

/**
 * Created by chris on 10/20/16.
 */

@Parcel
public class FilterConfig {
    private long beginDateMicros;
    private long endDateMicros;
    private int page = 0;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getBeginDateMicros() {
        return beginDateMicros;
    }

    public void setBeginDateMicros(long beginDateMicros) {
        this.beginDateMicros = beginDateMicros;
    }

    public long getEndDateMicros() {
        return endDateMicros;
    }

    public void setEndDateMicros(long endDateMicros) {
        this.endDateMicros = endDateMicros;
    }

    private String query;

    public List<String> getNewsDeskFilters() {
        return newsDeskFilters;
    }

    public void setNewsDeskFilters(List<String> newsDeskFilters) {
        this.newsDeskFilters = newsDeskFilters;
    }

    private List<String> newsDeskFilters;
    private String sortOrder = "newest";

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
