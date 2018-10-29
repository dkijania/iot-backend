package com.chariot.backend.measurement.persist.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by Nobody on 6/24/2017.
 */
public class MeasurementEntity {

    @Id
    public ObjectId id;

    private Date timestamp;

    private float value;

    private String channelId;

    public MeasurementEntity() {
    }

    public MeasurementEntity(Date timestamp, float value, String channelId) {
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeasurementEntity)) return false;

        MeasurementEntity that = (MeasurementEntity) o;

        if (Float.compare(that.getValue(), getValue()) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (getTimestamp() != null ? !getTimestamp().equals(that.getTimestamp()) : that.getTimestamp() != null)
            return false;
        return getChannelId() != null ? getChannelId().equals(that.getChannelId()) : that.getChannelId() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
        result = 31 * result + (getValue() != +0.0f ? Float.floatToIntBits(getValue()) : 0);
        result = 31 * result + (getChannelId() != null ? getChannelId().hashCode() : 0);
        return result;
    }
}
