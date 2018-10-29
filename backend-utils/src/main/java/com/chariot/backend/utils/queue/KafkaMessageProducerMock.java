package com.chariot.backend.utils.queue;

import com.chariot.backend.schema.ack.MessageAcknowledgement;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

public class KafkaMessageProducerMock {

    private final AcknowledgementKafkaTemplate<String, String> template;

    public KafkaMessageProducerMock(Map<String, Object> senderProperties, String topic){
        ProducerFactory<String, String> producerFactory =
                new DefaultKafkaProducerFactory<>(senderProperties);

        template = new AcknowledgementKafkaTemplate<>(producerFactory);
        template.setDefaultTopic(topic);
    }

    public void sendMessage(String content) throws Exception {
        MessageAcknowledgement acknowledgement = template.sendAndGetAcknowledgement(content);
    }
}
