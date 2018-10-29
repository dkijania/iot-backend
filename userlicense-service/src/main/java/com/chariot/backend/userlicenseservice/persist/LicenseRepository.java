package com.chariot.backend.userlicenseservice.persist;

import com.chariot.backend.model.LicenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Created by Dariusz Kijania on 7/27/2017.
 */
public interface LicenseRepository extends JpaRepository<LicenseEntity, Integer> {

    Optional<LicenseEntity> findOneByLicenseType(LicenseType licenseType);
}
