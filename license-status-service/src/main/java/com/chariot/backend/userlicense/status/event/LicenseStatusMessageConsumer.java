package com.chariot.backend.userlicense.status.event;

import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.schema.MeasurementMessage;
import com.chariot.backend.userlicense.status.service.LicenseLimitVerifier;
import com.chariot.backend.utils.http.response.HttpCallFailed;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class LicenseStatusMessageConsumer {

    @Autowired
    private LicenseLimitVerifier licenseLimitVerifier;

    @Autowired
    MeasurementMessageProducer measurementMessageProducer;

    @KafkaListener(topics = "${topic.notVerifiedMeasurement}")
    public void recieveMessage(MeasurementMessage message, Acknowledgment acknowledgment)
            throws HttpCallFailed, JsonProcessingException {
        String channelId = message.getChannelId();
        LicenseStatus licenseStatus = licenseLimitVerifier.getLicenseStatus(channelId);
        if(licenseStatus.isOk()) {
            acknowledgment.acknowledge();
            measurementMessageProducer.putMessageOnMeasurementTopic(message);
            return;
        }
        //For now, measage is discared. In future some failed transaction log will be added
        acknowledgment.acknowledge();
    }
}
