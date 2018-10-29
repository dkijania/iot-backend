package com.chariot.backend.schema.ack;

public class MessageAcknowledgement {
    private final MessageAcknowledgementStatus status;
    private final String ErrorMessage;

    public MessageAcknowledgement(MessageAcknowledgementStatus status, String errorMessage) {
        this.status = status;
        ErrorMessage = errorMessage;
    }

    public MessageAcknowledgementStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
