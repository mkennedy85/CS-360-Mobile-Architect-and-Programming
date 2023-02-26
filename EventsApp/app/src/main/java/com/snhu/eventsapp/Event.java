package com.snhu.eventsapp;

import java.io.Serializable;

public class Event implements Serializable {
    private long mId;
    private String mName;
    private String mDate;

    public Event() {};

    public Event(long id, String name, String date) {
        mId = id;
        mName = name;
        mDate = date;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
