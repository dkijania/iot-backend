package com.chariot.backend.measurement.rest.tests.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * Created by Dariusz Kijania on 7/2/2017.
 */
@Component
public class TestUtilities {

    public String toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r);
    }

}
