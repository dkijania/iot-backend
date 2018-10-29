package com.chariot.backend.notification.web;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chariot/notification/firebase")
public class SendNotificationController {

    @RequestMapping(path = "/send",
            method = RequestMethod.POST)
    @ApiOperation(value = "Puts new message on firebase topic")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void sendMessageToFirebaseTopic(@RequestParam(value = "topic") String topic) throws Throwable {
        {

            Message message = Message.builder()
                    .putData("score", "850")
                    .putData("time", "2:45")
                    .setTopic(topic)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        }
    }
}
