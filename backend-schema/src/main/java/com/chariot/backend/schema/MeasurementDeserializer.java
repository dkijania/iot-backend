package com.chariot.backend.schema;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class MeasurementDeserializer extends JsonDeserializer<MeasurementMessage> {

    public MeasurementDeserializer() {
        super(MeasurementMessage.class);
    }

}