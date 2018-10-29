package com.chariot.backend.model;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
public class License {
    private int requestsPerSeconds;
    private LicenseType licenseType;

    public License(int requestsPerSeconds, LicenseType licenseType) {
        this.requestsPerSeconds = requestsPerSeconds;
        this.licenseType = licenseType;
    }

    public License() {
    }

    public int getRequestsPerSeconds() {
        return requestsPerSeconds;
    }

    public void setRequestsPerSeconds(int requestsPerSeconds) {
        this.requestsPerSeconds = requestsPerSeconds;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
