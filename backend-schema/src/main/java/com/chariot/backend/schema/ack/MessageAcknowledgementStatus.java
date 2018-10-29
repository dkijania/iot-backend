package com.chariot.backend.schema.ack;

public enum MessageAcknowledgementStatus {
    SUCCESS("SUCCESS"), FAILURE("FAILURE");

    private final String name;

    MessageAcknowledgementStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
