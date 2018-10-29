package com.chariot.backend.userlicenseservice.web;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.User;
import com.chariot.backend.userlicenseservice.service.IChannelApi;
import com.chariot.backend.userlicenseservice.service.user.IUserApi;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@RestController
@RequestMapping("/chariot/channel")
public class ChannelController {

    @Autowired
    private IChannelApi channelApi;

    @Autowired
    private IUserApi userApi;

    @RequestMapping(path = "/create",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create New Channel", response = Channel.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Channel createForUser(@RequestParam String userName, @RequestParam String channelName)
            throws NamedEntityNotFoundException {
        User user = userApi.getUser(userName);
        return channelApi.createNewChannel(user, channelName);
    }

    @RequestMapping(path = "/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Returning channels", response = Channel.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Channel> getAll() {
        return channelApi.getAllChannels();
    }

    @RequestMapping(path = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Returning user channels", response = Channel.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Channel> getByUser(String userName) throws NamedEntityNotFoundException {
        return channelApi.getChannelByUser(userName);
    }

}