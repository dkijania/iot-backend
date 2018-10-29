package com.chariot.backend.test.integration;

import com.chariot.backend.test.integration.rest.AddressProvider;
import com.chariot.backend.utils.http.RestClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

@WebMvcTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.chariot.backend.test.integration","com.chariot.backend.utils"})
@ContextConfiguration(classes = {com.chariot.backend.test.integration.config.TestConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
public class CreateNewMeasurementIT {

    @Autowired
    private AddressProvider addressProvider;

    @Autowired
    private RestClient request;

   /* @Test
    public void setupData() throws Exception {

        addressProvider.getHardwareGatewayServiceAddress();
        String userName = "Dariusz2";

        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", userName);
        uriParams.add("password", userName);

        ResponseEntity<String> response = request.put(uriParams,
                addressProvider.getWebappGatewayServiceAddress() + "/chariot/gateway/webapp/user/create", String.class);
        checkError(response);

        uriParams = new LinkedMultiValueMap<>();
        uriParams.add("requestsPerSeconds", "10");
        uriParams.add("newLicenseType", "Standard");

        response = request.put(uriParams,
                addressProvider.getWebappGatewayServiceAddress() + "/chariot/gateway/webapp/license/create", String.class);
        checkError(response);

        uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", userName);
        uriParams.add("channelName", "channel");

        ResponseEntity<Channel> responseChannel = request.put(uriParams,
                addressProvider.getWebappGatewayServiceAddress() + "/chariot/gateway/webapp/channel/create", Channel.class);
        checkError(responseChannel);

        Channel channel = responseChannel.getBody();

        uriParams = new LinkedMultiValueMap<>();
        uriParams.add("value", "10");
        uriParams.add("channelId", channel.getId().toString());

        response = request.post(uriParams,
                addressProvider.getHardwareGatewayServiceAddress() + "/chariot/gateway/hardware/newValue");
        checkError(response);
    }

    private void checkError(ResponseEntity response) throws Exception {
        if (!Arrays.asList(200, 201).contains(response.getStatusCodeValue()))
            throw new Exception(String.valueOf(response.getBody()));
    }*/

}

