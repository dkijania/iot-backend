package com.chariot.backend.gateway.webapp.rest.impl.mock;

import com.chariot.backend.model.ChannelNotification;
import com.chariot.backend.schema.notification.NotificationRegistrationToken;
import com.kinoroy.expo.push.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class NotificationMockApi {
    private Map<String, String> tokens = new HashMap<>();

    public void registerToken(NotificationRegistrationToken notificationRegistrationToken) {
        tokens.put(notificationRegistrationToken.getUsername(), notificationRegistrationToken.getToken());
    }

    public void pushNotification(ChannelNotification channelNotification) throws Throwable {
        Collection<String> somePushTokens = tokens.values();
        List<Message> messages = new ArrayList<>();
        for (String token : somePushTokens) {
            if (!ExpoPushClient.isExpoPushToken(token)) {
                System.out.println(token + " is not a valid Expo Push Token!");
            }
        }
        for (String token : somePushTokens) {

            HashMap<String, Object> data = new HashMap<>();
            data.put("key", "value");


            Message message = new Message.Builder()
                    .to(token)
                    .title(channelNotification.getTitle())
                    .body(channelNotification.getBody())
                    .data(channelNotification.getData())
                    .build();
            messages.add(message);
        }

        List<List<Message>> chunks = ExpoPushClient.chunkItems(messages);

        for (List<Message> chunk : chunks) {
            try {
                PushTicketResponse response = ExpoPushClient.sendPushNotifications(messages);
                List<ExpoError> errors = response.getErrors();
                if (errors != null) {
                    for (ExpoError error : errors) {
                        // Handle the errors
                    }
                }
                List<PushTicket> tickets = response.getTickets();
                if (tickets != null) {
                    for (PushTicket ticket : tickets) {
                        if (ticket.getStatus() == Status.OK) {
                            String id = ticket.getId();
                        } else {
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
                System.out.println(e.getMessage());
            }
        }

    }


}
