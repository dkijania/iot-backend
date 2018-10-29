package com.chariot.backend.test.integration.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/*Created by Nobody on 6/24/2017.
 */
@Configuration
@EnableAutoConfiguration
public class TestConfiguration {

    private final Properties props = new Properties();

    public TestConfiguration() throws IOException {
        props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
    }

    public int getMeasurementServicePort() {
        return Integer.parseInt(props.getProperty("measurementServicePort"));
    }

    public int getUserLicenseServicePort() {
        return Integer.parseInt(props.getProperty("userlicenseServicePort"));
    }

    public int getLicenseStatusServicePort() {
        return Integer.parseInt(props.getProperty("licenseStatusServicePort"));
    }

    public int getGatewayHardwarePort() {
        return Integer.parseInt(props.getProperty("gatewayHardwarePort"));
    }

    public int getGatewayWebappPort() {
        return Integer.parseInt(props.getProperty("gatewayWebappPort"));
    }
}