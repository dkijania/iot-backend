package com.chariot.backend.utils.http;

import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

/**
 * Created by Dariusz Kijania on 8/13/2017.
 */
@Service
public class RestClient {

    protected RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public <T> ResponseEntity<T> get(String url, Class<T> type) {
        return get(new LinkedMultiValueMap<>(), url, type);
    }

    public <T> ResponseEntity<T> get(MultiValueMap<String, String> uriParams, String url, Class<T> type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);


        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = getRestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.queryParams(uriParams);

        return restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                type);
    }


    public <T> ResponseEntity<T> putData(T requestData, String url, Class<T> type) {
        RestTemplate restTemplate = getRestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpEntity<T> requestEntity = new HttpEntity<>(requestData);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, type);

    }

    public <T> ResponseEntity<T> put(MultiValueMap<String, String> uriParams, String url, Class<T> type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.queryParams(uriParams);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = getRestTemplate();

        return restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.PUT,
                entity,
                type);
    }

    public ResponseEntity<String> post(MultiValueMap<String, String> uriParams, String url) {
        return post(uriParams, url, String.class);
    }

    public <T> ResponseEntity<T> post(MultiValueMap<String, String> uriParams, String url, Class<T> type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(uriParams, headers);
        RestTemplate restTemplate = getRestTemplate();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.MULTIPART_FORM_DATA));
        restTemplate.getMessageConverters().add(formHttpMessageConverter);
        return restTemplate.postForEntity(url, request, type);
    }
}
