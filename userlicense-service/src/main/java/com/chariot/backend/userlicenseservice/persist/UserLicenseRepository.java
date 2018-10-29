package com.chariot.backend.userlicenseservice.persist;

import org.springframework.data.repository.Repository;

import java.util.Optional;


/**
 * Created by Dariusz Kijania on 7/30/2017.
 */
public interface UserLicenseRepository extends Repository<UserLicenseEntity, Integer> {
    void save(UserLicenseEntity userLicenseEntity);

    Optional<UserLicenseEntity> findByUser(UserEntity user);
}
