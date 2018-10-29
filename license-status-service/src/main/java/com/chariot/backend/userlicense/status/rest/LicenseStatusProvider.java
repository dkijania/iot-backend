package com.chariot.backend.userlicense.status.rest;

import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.userlicense.status.config.Configuration;
import com.chariot.backend.utils.http.RestClientWithVerify;
import com.chariot.backend.utils.http.response.HttpCallFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class LicenseStatusProvider {

    @Autowired
    private Configuration configuration;

    @Autowired
    private RestClientWithVerify restClientWithVerify;


    public String getUrlToLicenseStatusEndpoint() {
        return String.format("http://%s/chariot/license/status", configuration.getUserLicenseServiceUrl());
    }

    public ResponseEntity<LicenseStatus> getLicenseStatusFromUserLicenseService(String channelId)
            throws HttpCallFailed {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("channelId", channelId);

        String url = getUrlToLicenseStatusEndpoint();

        return restClientWithVerify.get(params, url, LicenseStatus.class);
    }
}