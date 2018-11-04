package com.chariot.backend.gateway.webapp.rest.impl.mock.rule;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.ChannelNotification;
import com.chariot.backend.model.Measurement;

import java.util.HashMap;

public class NewMeasurementValueRule implements INotificationRule {

    public boolean shouldSendNotification(Measurement measurement) {
        return true;
    }

    @Override
    public ChannelNotification getNotification(Measurement measurement, Channel channel) {
        return new ChannelNotification(channel.getName() + " Alert",
                String.format("New measurement added (%.2f)", measurement.getValue()), new HashMap<>());
    }
}
