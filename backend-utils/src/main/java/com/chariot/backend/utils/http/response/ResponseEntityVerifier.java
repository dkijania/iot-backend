package com.chariot.backend.utils.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ResponseEntityVerifier {

    public static final List<HttpStatus> correctHttpStatuses = Arrays.asList(HttpStatus.ACCEPTED, HttpStatus.OK);

    public void verifyNotFailed(ResponseEntity responseEntity) throws HttpCallFailed {
        if (correctHttpStatuses.contains(responseEntity.getStatusCode()))
            return;

        throw new HttpCallFailed(responseEntity.getStatusCode(), responseEntity.toString());
    }
}
