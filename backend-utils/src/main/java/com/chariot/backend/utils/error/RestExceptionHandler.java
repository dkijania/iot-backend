package com.chariot.backend.utils.error;

import com.chariot.backend.utils.error.exception.NamedIllegalArgumentException;
import com.chariot.backend.utils.error.reponse.ApiError;
import com.chariot.backend.utils.error.reponse.ApiValidationError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Service
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) throws JsonProcessingException {
        return new ResponseEntity<>(objectMapper.writeValueAsString(apiError), apiError.getStatus());
    }

    @ExceptionHandler(NamedIllegalArgumentException.class)
    protected ResponseEntity<Object> handleNamedIllegalArgumentException(NamedIllegalArgumentException ex) throws JsonProcessingException {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Wrong argument(s)");

        ApiValidationError apiValidationError = new ApiValidationError(ex.getArgumentName(),
                ex.getRejectedValue(), ex.getMessage());

        apiError.getSubErrors().add(apiValidationError);
        return buildResponseEntity(apiError);
    }
}