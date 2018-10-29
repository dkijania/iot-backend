package com.chariot.backend.userlicenseservice.service.license;

import com.chariot.backend.model.License;
import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.model.LicenseType;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@Service
public interface ILicenseApi {
    License getLicenseForChannelId(UUID id) throws NamedEntityNotFoundException;

    License getLicenseForChannelId(String id) throws LicenseApiException, NamedEntityNotFoundException;

    void changeLicenseTypeForUser(String userName, LicenseType newLicenseType) throws NamedEntityNotFoundException;

    void createNewLicense(License license);

    LicenseStatus getLicenseStatus(String channelId) throws NamedEntityNotFoundException;
}
