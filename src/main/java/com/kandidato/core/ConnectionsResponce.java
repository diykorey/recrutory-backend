package com.kandidato.core;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConnectionsResponce {
    @SerializedName("_count")
    private int count;
    @SerializedName("_start")
    private int start;
    @SerializedName("_total")
    private int total;

    private List<Person> values;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Person> getValues() {
        return values;
    }

    public void setValues(List<Person> values) {
        this.values = values;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
