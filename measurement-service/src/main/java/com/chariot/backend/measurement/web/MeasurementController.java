package com.chariot.backend.measurement.web;

import com.chariot.backend.measurement.service.MeasurementService;
import com.chariot.backend.model.Measurement;
import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import com.chariot.backend.utils.error.exception.NamedIllegalArgumentException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nobody on 6/24/2017.
 */
@RestController
@RequestMapping("/chariot/measurement")
public class MeasurementController {

    @Autowired
    private MeasurementService api;

    @Autowired
    private CurrentDateTimeProvider dateTimeProvider;


    @RequestMapping(path = "/new",
            method = RequestMethod.POST)
    @ApiOperation(value = "Puts new measurement for given channel")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void send(@RequestParam(value = "newValue") String newValue,
                     @RequestParam(value = "channelId") String channelId) throws Throwable {
        validateNewMeasurementArgument(newValue, "newValue");
        api.addMeasurement(dateTimeProvider.getCurrentDate(), newValue, channelId);
    }

    public float validateNewMeasurementArgument(String newValue, String newValueName) throws NamedIllegalArgumentException {
        try {
            return Float.parseFloat(newValue);
        } catch (NumberFormatException ex) {
            throw new NamedIllegalArgumentException(newValueName, newValue, String.format(
                    "Cannot parse '%s' as new measurement value to float. Because: %s", newValue, ex.getMessage()));
        }
    }

    @RequestMapping(path = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "View a list of store measurements", response = List.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Measurement> get(String channelId) {
        return api.getMeasurementsByChannel(channelId);
    }
}
