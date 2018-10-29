package com.chariot.backend.userlicenseservice.service.user;

import com.chariot.backend.model.User;
import com.chariot.backend.userlicenseservice.persist.UserRepositoryImpl;
import com.chariot.backend.userlicenseservice.service.mapper.ModelEntityMapper;
import com.chariot.backend.userlicenseservice.service.user.authenticate.UserAuthenticationApi;
import com.chariot.backend.userlicenseservice.service.user.create.NewUserApi;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dariusz Kijania on 7/28/2017.
 */
@Service
public class UserApi implements IUserApi {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private ModelEntityMapper mapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    NewUserApi newUserApi;

    @Autowired
    UserAuthenticationApi userAuthenticationApi;

    @Override
    public void createUser(String userName, String password) {
        newUserApi.createUser(userName, password);
    }

    @Override
    public User getUser(String userName) throws NamedEntityNotFoundException {
        return mapper.toUser(userRepository.getByName(userName));
    }

    @Override
    public boolean authenticateUser(String userName, String password)
            throws UserException, NamedEntityNotFoundException {
        return userAuthenticationApi.checkCredentials(userName, password);
    }

    @Override
    public List<User> listUsers() {
        return mapper.toUserModelList(userRepository.getAllUsers());
    }
}
