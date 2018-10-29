package com.chariot.backend.gateway.webapp.web;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.model.Channel;
import com.chariot.backend.model.LicenseStatus;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/chariot/gateway/webapp/channel")
public class ChannelController {

    @Autowired
    IWebAppRestClient remoteServiceCaller;

    @RequestMapping(path = "/create",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create New Channel", response = Channel.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Channel> createChannelForUser(@RequestParam String userName, @RequestParam String channelName) {
        return remoteServiceCaller.createChannelForUser(userName, channelName);
    }

    @RequestMapping(path = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Lists channels for user name", response = Channel.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Collection<Channel> listChannelForUser(@RequestParam String userName) {
        return remoteServiceCaller.listChannels(userName);
    }

    @RequestMapping(path = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Gets channel by channel id", response = Channel.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<Channel> getChannelById(@RequestParam String channelId) {
        return remoteServiceCaller.getChannel(channelId);
    }


    @RequestMapping(path = "/delete",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Deletes channel by id", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteChannel(@RequestParam String channelId) {
        remoteServiceCaller.deleteChannel(channelId);
    }


    @RequestMapping(path = "/check",
            method = RequestMethod.GET)
    @ApiOperation(value = "Gets license status for channel Id")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<LicenseStatus> getLicenseStatus(@RequestParam String channelId) throws IOException {
        return remoteServiceCaller.getLicenseStatus(channelId);
    }
}
