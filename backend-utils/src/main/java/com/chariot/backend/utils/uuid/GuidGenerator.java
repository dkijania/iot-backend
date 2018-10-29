package com.chariot.backend.utils.uuid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/24/2017.
 */
@Service
public class GuidGenerator {
    @Autowired
    private UUIDStringConverter converter;

    public UUID generateNewChannelId() {
        return UUID.randomUUID();
    }

    public synchronized String generateNewChannelIdAsString() {
        return converter.UUIDToString(generateNewChannelId());
    }

}
