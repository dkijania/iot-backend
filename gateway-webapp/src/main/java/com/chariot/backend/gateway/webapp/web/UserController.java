package com.chariot.backend.gateway.webapp.web;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/chariot/gateway/webapp/user")
public class UserController {

    @Autowired
    IWebAppRestClient remoteServiceCaller;

    @RequestMapping(path = "/create",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<User> createUser(String userName, String password) {
        return remoteServiceCaller.createUser(userName, password);
    }

    @RequestMapping(path = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<User> getUser(String userName) {
        return remoteServiceCaller.getUserByName(userName);
    }

    @RequestMapping(path = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Collection<User>> listUsers() {
        return new ResponseEntity<>(remoteServiceCaller.listUsers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/authenticate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Authenticate user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> authenticateUser(String username, String password) {
        return remoteServiceCaller.authenticateUser(username, password);
    }
}
