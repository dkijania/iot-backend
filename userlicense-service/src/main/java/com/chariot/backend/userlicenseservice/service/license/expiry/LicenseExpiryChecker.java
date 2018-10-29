package com.chariot.backend.userlicenseservice.service.license.expiry;

import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.model.UserLicense;
import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dariusz Kijania on 8/14/2017.
 */
@Service
public class LicenseExpiryChecker {

    @Autowired
    private CurrentDateTimeProvider currentDateTimeProvider;

    public LicenseStatus check(UserLicense userLicense) {
        LicenseStatus licenseStatus = new LicenseStatus();
     //   licenseStatus.setUserLicense(userLicense);
        evaluateExpiration(licenseStatus, userLicense);
        setComment(licenseStatus, userLicense);
        return licenseStatus;
    }

    private void setComment(LicenseStatus licenseStatus, UserLicense userLicense) {
        if (licenseStatus.isExpired()) {
            licenseStatus.setComment(String.format("License for user %s of type %s is expired from date %s.",
                    userLicense.getUser().getName(), userLicense.getLicense().getLicenseType(), userLicense.getExpiryDate()));
        } else {
            licenseStatus.setComment(String.format("License for user %s active.",
                    userLicense.getUser().getName()));
        }
    }

    private void evaluateExpiration(LicenseStatus licenseStatus, UserLicense userLicense) {
        licenseStatus.setExpired(userLicense.getExpiryDate().before(currentDateTimeProvider.getCurrentDate()));
    }
}
