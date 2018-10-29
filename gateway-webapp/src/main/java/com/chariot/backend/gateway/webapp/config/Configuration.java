package com.chariot.backend.gateway.webapp.config;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.gateway.webapp.rest.impl.WebAppRestClient;
import com.chariot.backend.gateway.webapp.rest.impl.WebAppRestClientMock;
import com.chariot.backend.utils.http.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties()
public class Configuration {

    private String userLicenseServiceUrl;

    public String getUserLicenseServiceUrl() {
        return userLicenseServiceUrl;
    }

    public void setUserLicenseServiceUrl(String userLicenseServiceUrl) {
        this.userLicenseServiceUrl = userLicenseServiceUrl;
    }


    @Bean
    @ConditionalOnProperty(name = "actAsMock",
            havingValue = "false")
    @Primary
    public IWebAppRestClient remoteServiceCaller() {
        return new WebAppRestClient();
    }

    @Bean
    @ConditionalOnProperty(name = "actAsMock",
            havingValue = "true")
    @Primary
    public IWebAppRestClient remoteServiceCallerMock() {
        return new WebAppRestClientMock();
    }

    @Bean
    public RestClient restClient()
    {
        return new RestClient();
    }

    @Bean
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }


}