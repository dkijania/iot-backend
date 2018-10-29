package com.chariot.backend.test.integration.rest;

import com.chariot.backend.test.integration.config.TestConfiguration;
import com.chariot.backend.utils.docker.DockerInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class AddressProvider {

    @Autowired
    private DockerInfoProvider dockerInfoProvider;

    @Autowired
    private TestConfiguration testProperties;

    public String getUserLicenseServiceAddress() throws URISyntaxException {
        return dockerInfoProvider.getAddress(testProperties.getUserLicenseServicePort());
    }

    public String getLicenseStatusServiceAddress() throws URISyntaxException {
        return dockerInfoProvider.getAddress(testProperties.getLicenseStatusServicePort());
    }

    public String getMeasurementServiceAddress() throws URISyntaxException {
        return dockerInfoProvider.getAddress(testProperties.getMeasurementServicePort());
    }

    public String getHardwareGatewayServiceAddress() throws URISyntaxException {
        return dockerInfoProvider.getAddress(testProperties.getGatewayHardwarePort());
    }

    public String getWebappGatewayServiceAddress() throws URISyntaxException {
        return dockerInfoProvider.getAddress(testProperties.getGatewayWebappPort());
    }

}
