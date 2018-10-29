package com.chariot.backend.userlicenseservice.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Dariusz Kijania on 7/27/2017.
 */
@Transactional
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByName(String name);
}
