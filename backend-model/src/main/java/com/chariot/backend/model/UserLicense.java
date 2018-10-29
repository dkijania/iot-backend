package com.chariot.backend.model;

import java.util.Date;

/**
 * Created by Dariusz Kijania on 7/30/2017.
 */
public class UserLicense {
    private User user;
    private License license;
    private Date expiryDate;

    public UserLicense() {
    }

    public UserLicense(User user, License license, Date expiryDate) {
        this.user = user;
        this.license = license;
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
