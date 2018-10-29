package com.chariot.backend.utils.queue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaConsumerMock {

    private final Map<String, Object> consumerProps;
    private final String topicName;

    private String lastResponse;

    public String getLastMessage() {
        return lastResponse;
    }
    public KafkaConsumerMock(Map<String, Object> consumerProps, String topicName) {
        this.consumerProps = consumerProps;
        this.topicName = topicName;
    }

    public void subscribe() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            KafkaConsumer<Integer, String> kafkaConsumer = new KafkaConsumer<>(consumerProps);
            kafkaConsumer.subscribe(Collections.singletonList(topicName));
            try {
                while (true) {
                    ConsumerRecords<Integer, String> records = kafkaConsumer.poll(100);
                    for (ConsumerRecord<Integer, String> record : records) {
                        System.out.println(String.format("consuming from topic = %s, value = %s", record.topic(), record.value()));
                        lastResponse = record.value();
                        latch.countDown();
                    }
                }
            } finally {
                kafkaConsumer.close();
            }
        });

        latch.await(90, TimeUnit.SECONDS);
    }

}
