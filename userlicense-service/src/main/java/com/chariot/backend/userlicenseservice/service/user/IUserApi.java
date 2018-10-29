package com.chariot.backend.userlicenseservice.service.user;

import com.chariot.backend.model.User;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dariusz Kijania on 7/28/2017.
 */
@Service
public interface IUserApi {
    void createUser(String userName, String password);

    User getUser(String userName) throws NamedEntityNotFoundException;

    boolean authenticateUser(String userName, String password) throws UserException, NamedEntityNotFoundException;

    List<User> listUsers();
}
