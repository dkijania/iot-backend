package com.chariot.backend.gateway.hardware.web;

import com.chariot.backend.gateway.hardware.event.HardwareKafkaMessageProducer;
import com.chariot.backend.schema.ack.MessageAcknowledgement;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Dariusz Kijania on 8/20/2017.
 */
@RestController
@RequestMapping("/chariot/gateway/hardware")
public class HardwareGatewayController {

    @Autowired
    private HardwareKafkaMessageProducer hardwareKafkaMessageProducer;

    @RequestMapping(path = "/newValue",
            method = RequestMethod.POST)
    @ApiOperation(value = "Puts new measurement")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageAcknowledgement putValue(@RequestParam Double value, @RequestParam String channelId) throws IOException {
        return hardwareKafkaMessageProducer.putMessageOnMeasurementTopic(value, channelId);
    }
}
