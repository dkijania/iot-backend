package com.chariot.backend.userlicenseservice.persist;

import org.springframework.data.repository.Repository;

public interface UserLoginLockRepository extends Repository<UserLoginLockEntity, Integer>  {
    void save(UserLoginLockEntity userLoginLock);
    UserLoginLockEntity findLastByUser(UserEntity userEntity);
}
