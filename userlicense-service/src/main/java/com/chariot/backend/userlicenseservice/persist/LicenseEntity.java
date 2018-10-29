package com.chariot.backend.userlicenseservice.persist;

import com.chariot.backend.model.LicenseType;

import javax.persistence.*;


/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@Entity
@Table(name = "license")
public class LicenseEntity {

    private long id;
    private int requestsPerSeconds;
    private LicenseType licenseType;

    public LicenseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "requests_per_seconds")
    public int getRequestsPerSeconds() {
        return requestsPerSeconds;
    }

    public void setRequestsPerSeconds(int requestsPerSeconds) {
        this.requestsPerSeconds = requestsPerSeconds;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, name = "license_type")
    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }
}
