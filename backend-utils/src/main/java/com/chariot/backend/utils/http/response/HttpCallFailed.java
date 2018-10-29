package com.chariot.backend.utils.http.response;

import org.springframework.http.HttpStatus;

public class HttpCallFailed extends Exception {

    private HttpStatus httpStatus;

    public HttpCallFailed(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
