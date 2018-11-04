package com.chariot.backend.schema.notification;

public class NotificationRegistrationToken {
    private String token;
    private String username;

    public NotificationRegistrationToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public NotificationRegistrationToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
