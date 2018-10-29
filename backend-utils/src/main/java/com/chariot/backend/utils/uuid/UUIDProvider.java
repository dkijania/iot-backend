package com.chariot.backend.utils.uuid;

import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/28/2017.
 */
@Component
public class UUIDProvider {
    @Autowired
    private UUIDStringConverter converter;

    @Autowired
    private GuidGenerator generator;

    public String UUIDToString(UUID uuid) {
        return converter.UUIDToString(uuid);
    }

    public UUID stringToUUID(String id) throws DecoderException {
        return converter.stringToUUID(id);
    }

    public UUID generateNewChannelId() {
        return generator.generateNewChannelId();
    }

    public String generateNewChannelIdAsString() {
        return generator.generateNewChannelIdAsString();
    }
}
