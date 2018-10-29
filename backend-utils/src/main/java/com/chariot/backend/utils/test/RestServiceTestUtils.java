package com.chariot.backend.utils.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceTestUtils {

    @Autowired
    protected ObjectMapper objectMapper;

    public String getSUTAddress(int port) {
        return "http://localhost:" + port;
    }

    public String convertToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
