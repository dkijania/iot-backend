package com.chariot.backend.utils.http;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Won't throw exception on 400+ or 500+ http status
 */
@Service
public class RestClientSafe extends RestClient {

    @Override
    protected RestTemplate getRestTemplate() {
        RestTemplate restTemplate = super.getRestTemplate();
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // do nothing
            }
        });
        return restTemplate;
    }
}
