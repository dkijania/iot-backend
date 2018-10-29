package com.chariot.backend.schema;

public class MeasurementMessage {
    public String channelId;
    public Double value;

    public MeasurementMessage() {
    }

    public MeasurementMessage(String channelId, Number value) {
        this(channelId,(double)value);
    }

    public MeasurementMessage(String channelId, Double value) {
        this.channelId = channelId;
        this.value = value;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
