package com.chariot.backend.notification.web;

import com.chariot.backend.notification.api.NotificationApi;
import com.chariot.backend.schema.notification.NotificationRegistrationToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/chariot/notification")
public class RecieveTokenController {

    @RequestMapping(path = "/push-token",
            method = RequestMethod.POST)
    @ApiOperation(value = "Register new token")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void sendMessageToFirebaseTopic(@RequestBody NotificationRegistrationToken notificationRegistrationToken)
            throws Throwable {
        {
            NotificationApi.tokens.put(notificationRegistrationToken.getUsername(), notificationRegistrationToken.getToken());
        }
    }
}
