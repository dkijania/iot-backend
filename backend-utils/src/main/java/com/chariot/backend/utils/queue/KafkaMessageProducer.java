package com.chariot.backend.utils.queue;

import com.chariot.backend.schema.ack.MessageAcknowledgement;
import com.chariot.backend.schema.ack.MessageAcknowledgementStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaMessageProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public MessageAcknowledgement send(String topic, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic,payload);
        return new MessageAcknowledgement(MessageAcknowledgementStatus.SUCCESS,"");
    }
}
