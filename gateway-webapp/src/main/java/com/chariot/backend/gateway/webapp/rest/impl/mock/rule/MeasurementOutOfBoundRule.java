package com.chariot.backend.gateway.webapp.rest.impl.mock.rule;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.ChannelNotification;
import com.chariot.backend.model.Measurement;
import com.chariot.backend.utils.data.FloatingPointComparator;

import java.util.HashMap;

public class MeasurementOutOfBoundRule implements INotificationRule {
    private final double upperBound;
    private final double lowerBound;

    public MeasurementOutOfBoundRule(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean shouldSendNotification(Measurement measurement) {
        return new FloatingPointComparator().isWithinBounds(measurement.getValue(), upperBound, lowerBound);
    }

    @Override
    public ChannelNotification getNotification(Measurement measurement, Channel channel) {
        return new ChannelNotification(channel.getName() + " Alert",
                String.format("Recent measurement (%.2f)is out of bounds [%.2f-%.2f]", measurement.getValue(),
                        upperBound, lowerBound), new HashMap<>());
    }
}
