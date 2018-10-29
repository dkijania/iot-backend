package com.chariot.backend.userlicenseservice.persist;

import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryImpl {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getByName(String name) throws NamedEntityNotFoundException {
        return userRepository
                .findByName(name)
                .orElseThrow(() -> new NamedEntityNotFoundException(String.format("User with name '%s' not found", name)
                        , "user", name));
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveOrUpdate(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
