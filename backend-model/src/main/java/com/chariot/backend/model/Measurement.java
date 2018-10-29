package com.chariot.backend.model;

import java.util.Date;

/**
 * Created by Nobody on 6/23/2017.
 */
public class Measurement {
    private Date timestamp;
    private double value;
    private String channelId;

    public Measurement() {
    }

    public Measurement(Date timestamp, double value, String channelId) {
        this.timestamp = timestamp;
        this.value = value;
        this.channelId = channelId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "timestamp=" + timestamp +
                ", value=" + value +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
