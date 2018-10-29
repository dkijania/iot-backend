package com.chariot.backend.measurement.rest.tests.base;

import com.chariot.backend.utils.queue.KafkaMessageProducerMock;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.springframework.kafka.test.rule.KafkaEmbedded;

public class MeasurementComponentTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
    }

    private KafkaMessageProducerMock kafkaMessageProducerMock;

    private static final String RECEIVER_TOPIC = "hardware.t";

    @ClassRule
    public static final KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

}
