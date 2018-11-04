package com.chariot.backend.model;

import java.util.HashMap;

public class ChannelNotification {
    private final String title;
    private final String body;
    private final HashMap<String, Object> data;

    public ChannelNotification(String title, String body, HashMap<String, Object> data) {
        this.title = title;
        this.body = body;
        this.data = data;
    }

    public ChannelNotification() {
        this("", "", new HashMap<>());
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
