package com.chariot.backend.notification.web;

import com.chariot.backend.notification.api.NotificationApi;
import com.kinoroy.expo.push.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/chariot/notification/")
public class SendNotificationController {

    @RequestMapping(path = "/push",
            method = RequestMethod.POST)
    @ApiOperation(value = "Push notification")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void pushNotification() throws Throwable {
        Collection<String> somePushTokens = NotificationApi.tokens.values();
        // Sending message
        List<Message> messages = new ArrayList<>();
        // You can check whether your push tokens are syntactically valid
        for (String token : somePushTokens) {
            // Each push token looks like ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]
            if (!ExpoPushClient.isExpoPushToken(token)) {
                System.out.println(token + " is not a valid Expo Push Token!");
            }
        }
        for (String token : somePushTokens) {
            // Construct a message

            HashMap<String,Object> data = new HashMap<>();
            data.put("key","value");


            Message message = new Message.Builder()
                    .to(token)
                    .title("You've got mail!")
                    .body("Check your messages")
                    .data(data)
                    .build();
            messages.add(message);
        }

        // The Expo push service accepts batches of messages, no more than 100 at a time.
        // If you know you're sending more than 100 messages,
        // Use ExpoPushClient.chunkItems to get lists of 100 or less items
        List<List<Message>> chunks = ExpoPushClient.chunkItems(messages);

        for (List<Message> chunk : chunks) {
            try {
                PushTicketResponse response = ExpoPushClient.sendPushNotifications(messages);
                List<ExpoError> errors = response.getErrors();
                // If there is an error with the *entire request*:
                // The errors object will be an list of errors,
                // (usually just one)
                if (errors != null) {
                    for (ExpoError error : errors) {
                        // Handle the errors
                    }
                }
                // If there are errors that affect individual messages but not the entire request,
                // errors will be null and each push ticket will individually contain the status
                // of each message (ok or error)
                List<PushTicket> tickets = response.getTickets();
                if (tickets != null) {
                    for (PushTicket ticket : tickets) {
                        // Handle each ticket (namely, check the status, and save the ID!)
                        // NOTE: If a ticket status is error, you can get the specific error
                        // from the details object. You must handle it appropriately.
                        // The error codes are listed in PushError
                        if (ticket.getStatus() == Status.OK) {
                            String id = ticket.getId();
                            // Save this id somewhere for later
                        } else {
                            // Handle the error
                            PushError e = ticket.getDetails().getError();
                            switch (e) {
                                case MESSAGE_TOO_BIG:
                                case INVALID_CREDENTIALS:
                                case DEVICE_NOT_REGISTERED:
                                case MESSAGE_RATE_EXCEEDED:
                            }

                        }
                    }
                }
            } catch (IOException e) {
                // Handle a network error here
                System.out.println(e.getMessage());
            }
        }

    }


}
