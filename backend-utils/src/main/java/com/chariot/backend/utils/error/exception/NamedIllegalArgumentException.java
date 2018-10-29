package com.chariot.backend.utils.error.exception;

public class NamedIllegalArgumentException extends Exception {

    private String argumentName;
    private String rejectedValue;
    private String object;

    public NamedIllegalArgumentException(String argumentName, String rejectedValue, String message) {
        super(message);
        this.argumentName = argumentName;
        this.rejectedValue = rejectedValue;
    }

    public String getArgumentName() {
        return argumentName;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
