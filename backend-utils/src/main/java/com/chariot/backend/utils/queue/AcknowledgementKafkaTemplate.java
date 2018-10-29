package com.chariot.backend.utils.queue;

import com.chariot.backend.schema.ack.MessageAcknowledgement;
import com.chariot.backend.schema.ack.MessageAcknowledgementBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class AcknowledgementKafkaTemplate<K, V> extends KafkaTemplate<K, V> {

    public AcknowledgementKafkaTemplate(ProducerFactory<K, V> producerFactory) {
        super(producerFactory);
    }

    public AcknowledgementKafkaTemplate(ProducerFactory<K, V> producerFactory, boolean autoFlush) {
        super(producerFactory, autoFlush);
    }

    public MessageAcknowledgement sendAndGetAcknowledgement(V value) {
        ListenableFuture<SendResult<K, V>> future = sendDefault(value);
        final MessageAcknowledgement[] acknowledgement = new MessageAcknowledgement[1];

        future.addCallback(new ListenableFutureCallback<SendResult<K, V>>() {

            @Override
            public void onSuccess(SendResult<K, V> result) {
                acknowledgement[0] = MessageAcknowledgementBuilder.Success();
            }

            @Override
            public void onFailure(Throwable ex) {
                acknowledgement[0] = MessageAcknowledgementBuilder.Failure(ex.getMessage());
            }

        });

        return getAcknowledgementIncludingPossibleException(future, acknowledgement[0]);
    }

    private MessageAcknowledgement getAcknowledgementIncludingPossibleException(
            ListenableFuture<SendResult<K, V>> future, MessageAcknowledgement acknowledgement) {
        try {
            future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            MessageAcknowledgementBuilder.Failure(e.getMessage());
        }
        return acknowledgement;
    }
}
