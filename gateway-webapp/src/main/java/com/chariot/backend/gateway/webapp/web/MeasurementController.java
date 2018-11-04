package com.chariot.backend.gateway.webapp.web;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chariot/gateway/webapp/measurement")
public class MeasurementController {

    @Autowired
    IWebAppRestClient remoteServiceCaller;

    @RequestMapping(path = "/newValue",
            method = RequestMethod.POST)
    @ApiOperation(value = "Puts new measurement")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void putNewMeasurement(@RequestParam Double value, @RequestParam String channelId) throws Throwable {
        remoteServiceCaller.putNewMeasurement(value, channelId);
    }
}