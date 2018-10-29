package com.chariot.backend.model;

import lombok.Data;

import java.time.Instant;

/**
 * Created by Dariusz Kijania on 8/14/2017.
 */
public @Data class RequestLog {
    private Instant timestamp;
    private String channelId;

}
