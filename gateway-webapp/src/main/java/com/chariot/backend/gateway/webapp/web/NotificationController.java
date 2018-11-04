package com.chariot.backend.gateway.webapp.web;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.schema.notification.NotificationRegistrationToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chariot/gateway/webapp/notification")
public class NotificationController {

    @Autowired
    IWebAppRestClient remoteServiceCaller;



    @RequestMapping(path = "/register-token",
            method = RequestMethod.POST)
    @ApiOperation(value = "Register new token")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void registerToken(@RequestBody NotificationRegistrationToken notificationRegistrationToken)
            throws Throwable {
        {
            remoteServiceCaller.registerNotificationToken(notificationRegistrationToken);
        }
    }

    @RequestMapping(path = "/setRule-outofbounds",
            method = RequestMethod.POST)
    @ApiOperation(value = "Register new token")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void setOutOfBoundNotificationRule(@RequestParam String channelId, @RequestParam double upperBound,
                                              @RequestParam double lowerBound)
    {
        remoteServiceCaller.setOutOfBoundNotificationRule(channelId,upperBound,lowerBound);
    }


    @RequestMapping(path = "/setRule-newmeasurement",
            method = RequestMethod.POST)
    @ApiOperation(value = "Register new token")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void setNewMeasurementNotificationRule(@RequestParam String channelId)
    {
        remoteServiceCaller.setNewMeasurementNotificationRule(channelId);
    }
}
