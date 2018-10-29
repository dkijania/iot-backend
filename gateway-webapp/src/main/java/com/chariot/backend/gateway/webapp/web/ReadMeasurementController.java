package com.chariot.backend.gateway.webapp.web;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.model.Measurement;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chariot/gateway/webapp/measurement")
public class ReadMeasurementController {

    @Autowired
    IWebAppRestClient remoteServiceCaller;

    @RequestMapping(path = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get measurements")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<Measurement>> getMeasurementForChannelId(@RequestParam String channelId) {
        return remoteServiceCaller.getMeasurementForChannelId(channelId);
    }


}
