package com.chariot.backend.userlicenseservice.web;

import com.chariot.backend.model.User;
import com.chariot.backend.schema.login.LoginResponse;
import com.chariot.backend.userlicenseservice.service.user.IUserApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@RestController
@RequestMapping("/chariot/user")
public class UserController {

    @Autowired
    private IUserApi userApi;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create new user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createUser(String username, String password) {
        userApi.createUser(username, password);
    }

    @RequestMapping(path = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> listUser() {
        return userApi.listUsers();
    }

    @RequestMapping(path = "/authenticate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Authenticate user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<LoginResponse> authenticateUser(String username, String password) {
        try {
            if (userApi.authenticateUser(username, password)) {
                return new ResponseEntity<>(new LoginResponse("Authenticated"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new LoginResponse("Unauthorized: Login failed"), HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            return new ResponseEntity<>(new LoginResponse("Unauthorized: " + e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
