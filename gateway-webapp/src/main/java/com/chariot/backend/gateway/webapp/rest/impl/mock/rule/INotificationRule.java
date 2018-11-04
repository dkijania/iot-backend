package com.chariot.backend.gateway.webapp.rest.impl.mock.rule;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.ChannelNotification;
import com.chariot.backend.model.Measurement;

public interface INotificationRule {
    boolean shouldSendNotification(Measurement measurement);
    ChannelNotification getNotification(Measurement measurement, Channel channel);
}
