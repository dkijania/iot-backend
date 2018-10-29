package com.chariot.backend.measurement.event;

import com.chariot.backend.measurement.service.MeasurementService;
import com.chariot.backend.model.Measurement;
import com.chariot.backend.schema.MeasurementMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MeasurementMessageConsumer {

    @Autowired
    private MeasurementService measurementService;

    @KafkaListener(topics = "${topic.measurement}")
    public void recieveMessage(MeasurementMessage message, Acknowledgment acknowledgment) {
        measurementService.addMeasurement(new Measurement(new Date(), message.getValue(), message.getChannelId()));
        acknowledgment.acknowledge();
    }

}
