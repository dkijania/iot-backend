package com.chariot.backend.gateway.webapp.rest.impl;

import com.chariot.backend.gateway.webapp.config.Configuration;
import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.model.*;
import com.chariot.backend.utils.http.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.List;

@Service
public class WebAppRestClient implements IWebAppRestClient {

    @Autowired
    private RestClient restClient;

    @Autowired
    private Configuration configuration;

    @Override
    public ResponseEntity<User> createUser(String userName, String password) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("username", userName);
        uriParams.add("password", password);
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/user/create";
        return restClient.put(uriParams, url, User.class);
    }

    @Override
    public ResponseEntity<License> createNewLicense(int requestsPerSeconds, LicenseType licenseType) {
        License license = new License(requestsPerSeconds, licenseType);
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/license/create";
        return restClient.putData(license, url, License.class);
    }

    @Override
    public ResponseEntity<License> changeLicense(String userName, LicenseType newLicenseType) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", userName);
        uriParams.add("newLicenseType", newLicenseType.toString());
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/license/change";
        return restClient.post(uriParams, url, License.class);
    }

    @Override
    public ResponseEntity<Channel> createChannelForUser(String userName, String channelName) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", userName);
        uriParams.add("channelName", channelName);
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/channel/create";
        return restClient.put(uriParams, url, Channel.class);
    }

    @Override
    public Collection<Channel> listChannels(String userName) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", userName);
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/channel/get";
        return restClient.get(uriParams, url, List.class).getBody();
    }

    @Override
    public ResponseEntity<String> authenticateUser(String username, String password) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", username);
        uriParams.add("password", password);
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/user/authenticate";
        return restClient.get(uriParams, url, String.class);
    }

    @Override
    public void deleteChannel(String channelId) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<LicenseStatus> getLicenseStatus(String channelId) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<License> getLicenseForChannelId(String channelId) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> listUsers() {
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/user/list";
        return restClient.get(url, List.class).getBody();
    }

    @Override
    public ResponseEntity<User> getUserByName(String userName) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("userName", userName);
        String url = configuration.getUserLicenseServiceUrl() + "/chariot/user/get";
        return restClient.get(uriParams, url, User.class);
    }

    @Override
    public ResponseEntity<Channel> getChannel(String channelId) {
        throw new NotImplementedException();
    }

    @Override
    public ResponseEntity<List<Measurement>> getMeasurementForChannelId(String channelId) {
        throw new NotImplementedException();
    }


}
