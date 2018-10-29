package com.chariot.backend.utils.http;

import com.chariot.backend.utils.http.response.HttpCallFailed;
import com.chariot.backend.utils.http.response.ResponseEntityVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class RestClientWithVerify {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ResponseEntityVerifier responseEntityVerifier;

    public <T> ResponseEntity<T> get(MultiValueMap<String, String> uriParams, String url, Class<T> type)
            throws HttpCallFailed {
        ResponseEntity<T> responseEntity = restClient.get(uriParams, url, type);
        responseEntityVerifier.verifyNotFailed(responseEntity);
        return responseEntity;
    }

    public <T> ResponseEntity<T> put(MultiValueMap<String, String> uriParams, String url, Class<T> type)
            throws HttpCallFailed {
        ResponseEntity<T> responseEntity = restClient.put(uriParams, url, type);
        responseEntityVerifier.verifyNotFailed(responseEntity);
        return responseEntity;
    }

    public ResponseEntity<String> post(MultiValueMap<String, String> uriParams, String url)
            throws HttpCallFailed {
        ResponseEntity<String> responseEntity = restClient.post(uriParams, url);
        responseEntityVerifier.verifyNotFailed(responseEntity);
        return responseEntity;
    }
}
