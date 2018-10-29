package com.chariot.backend.gateway.hardware.event;

import com.chariot.backend.schema.MeasurementMessage;
import com.chariot.backend.schema.ack.MessageAcknowledgement;
import com.chariot.backend.utils.queue.KafkaMessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HardwareKafkaMessageProducer {

    @Value("${topic.hardware}")
    private String topic;

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @Autowired
    ObjectMapper objectMapper;

    public MessageAcknowledgement putMessageOnMeasurementTopic(Double value, String channelId)
            throws JsonProcessingException {
        MeasurementMessage measurementMessage = new MeasurementMessage(channelId,value);
        String message = objectMapper.writeValueAsString(measurementMessage);
        return kafkaMessageProducer.send(topic, message);
    }

}
