package com.chariot.backend.schema.ack;

public class MessageAcknowledgementBuilder {

    public static MessageAcknowledgement Success() {
        return new MessageAcknowledgement(MessageAcknowledgementStatus.SUCCESS, "");
    }

    public static MessageAcknowledgement Failure(String message) {
        return new MessageAcknowledgement(MessageAcknowledgementStatus.SUCCESS, message);
    }
}
