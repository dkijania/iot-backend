package com.chariot.backend.utils.uuid;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/27/2017.
 */
@Service
public class UUIDStringConverter {

    public String UUIDToString(UUID uuid) {
        return removeDashes(uuid.toString());
    }

    private String removeDashes(String uuid){
        return uuid.replaceAll("-", "");
    }

    public UUID stringToUUID(String id) throws DecoderException {
        byte[] data = Hex.decodeHex(removeDashes(id).toCharArray());
        return new UUID(ByteBuffer.wrap(data, 0, 8).getLong(), ByteBuffer.wrap(data, 8, 8).getLong());
    }
}
