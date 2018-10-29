package com.chariot.backend.gateway.webapp.rest;

import com.chariot.backend.gateway.webapp.Application;
import com.chariot.backend.model.License;
import com.chariot.backend.model.LicenseType;
import com.chariot.backend.model.User;
import com.chariot.backend.utils.http.RestClient;
import com.chariot.backend.utils.test.RestServiceTestUtils;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@RunWith(SpringRunner.class)
public class RemoteServiceCallerTestUtils {

    private static final License license = new License(10, LicenseType.Trail);
    private static final User user = new User("Dariusz", "Kijania", license);

    @Autowired
    private RestServiceTestUtils restServiceTestUtils;

    @Autowired
    protected RestClient restClient;

    @Rule
    public WireMockRule service = new WireMockRule(9998);

    @LocalServerPort
    protected int SUTPort;

    @Before
    public void initMock() {
        stubFor(put(urlEqualTo("/chariot/user/create"))
                .withQueryParam("username", equalTo(user.getName()))
                .withQueryParam("password", equalTo(user.getPassword()))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                        ));
    }

    @Test
    public void verifyCreateUserEndpoint() {

        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("username", user.getName());
        uriParams.add("password", user.getPassword());

        ResponseEntity<Object> responseEntity = restClient.put(uriParams,
                restServiceTestUtils.getSUTAddress(SUTPort) + "/chariot/gateway/webapp/user/create",null);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
