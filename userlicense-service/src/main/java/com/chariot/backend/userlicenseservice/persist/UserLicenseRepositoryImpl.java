package com.chariot.backend.userlicenseservice.persist;

import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLicenseRepositoryImpl {

    @Autowired
    private UserLicenseRepository userLicenseRepository;


    public UserLicenseEntity getByUser(UserEntity userEntity) throws NamedEntityNotFoundException {
        return userLicenseRepository.findByUser(userEntity).orElseThrow(
                () -> new NamedEntityNotFoundException(
                        String.format("UserLicense entity not found for user '%s'", userEntity)
                        , "user", userEntity.getName()));
    }

    public void saveOrUpdate(UserLicenseEntity userLicenseEntity) {
        userLicenseRepository.save(userLicenseEntity);
    }
}
