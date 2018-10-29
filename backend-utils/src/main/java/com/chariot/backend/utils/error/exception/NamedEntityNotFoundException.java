package com.chariot.backend.utils.error.exception;

public class NamedEntityNotFoundException extends Exception {

    private String entityName;
    private String queryProperty;

    public NamedEntityNotFoundException(String message, String entityName, String queryProperty) {
        super(message);
        this.entityName = entityName;
        this.queryProperty = queryProperty;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getQueryProperty() {
        return queryProperty;
    }
}
