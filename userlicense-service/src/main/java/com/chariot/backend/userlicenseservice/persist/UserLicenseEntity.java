package com.chariot.backend.userlicenseservice.persist;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Dariusz Kijania on 7/30/2017.
 */
@Entity
@Table(name = "user_license")
public class UserLicenseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID",referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="license_id",referencedColumnName = "id")
    private LicenseEntity license;

    private Date expiryDate;

    public UserLicenseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LicenseEntity getLicense() {
        return license;
    }

    public void setLicense(LicenseEntity license) {
        this.license = license;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
