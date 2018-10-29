package com.chariot.backend.userlicenseservice.service.user.create;

import com.chariot.backend.model.LicenseType;
import com.chariot.backend.userlicenseservice.persist.*;
import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class NewUserApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLicenseRepository userLicenseRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private CurrentDateTimeProvider currrentDateTimeProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private LicenseEntity getInitLicense() {
        LicenseType licenseType = LicenseType.Trail;
        Optional<LicenseEntity> licenseEntityOptional = licenseRepository.findOneByLicenseType(licenseType);

        if (!licenseEntityOptional.isPresent())
            throw new EntityNotFoundException(String.format("License not found %s. " +
                    "This license should be available after setup.", licenseType));
        return licenseEntityOptional.get();
    }


    public void createUser(String userName, String password) {

        LicenseEntity licenseEntity = getInitLicense();

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userName);

        String hashedPassword = passwordEncoder.encode(password);
        userEntity.setPassword(hashedPassword);

        UserLicenseEntity userLicenseEntity = new UserLicenseEntity();
        userLicenseEntity.setUser(userEntity);
        userLicenseEntity.setLicense(licenseEntity);

        userLicenseEntity.setExpiryDate(currrentDateTimeProvider.getDateInFuture(14));

        userRepository.save(userEntity);
        userLicenseRepository.save(userLicenseEntity);
    }
}
