package com.chariot.backend.model;


import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
public enum LicenseType {
    Trail("Trail"),Standard("Standard"),Premium("Premium");

    private final String name;

    LicenseType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonCreator
    public static LicenseType fromText(String text)
    {
        return LicenseType.valueOf(text);
    }
}
