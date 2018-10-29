package com.chariot.backend.gateway.webapp.rest.impl;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.model.*;
import com.chariot.backend.utils.uuid.GuidGenerator;
import com.chariot.backend.utils.uuid.UUIDStringConverter;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class WebAppRestClientMock implements IWebAppRestClient {

    private final List<License> licenses = new LinkedList<>();
    private final List<User> users = new LinkedList<>();
    private final List<UserLicense> userLicenses = new LinkedList<>();
    private final List<Channel> channels = new LinkedList<>();
    private final List<Measurement> measurements = new LinkedList<>();

    @Autowired
    private GuidGenerator guidGenerator;

    @Autowired
    private UUIDStringConverter stringConverter;

    public WebAppRestClientMock() {
    }

    @PostConstruct
    public void init() {
        licenses.add(new License(5, LicenseType.Trail));
        licenses.add(new License(10, LicenseType.Standard));
        licenses.add(new License(100, LicenseType.Premium));

        User user = new User();
        user.setLicense(licenses.stream().findFirst().get());
        user.setName("Dariusz");
        user.setPassword("Kijania");
        users.add(user);

        channels.add(createChannelForUser(user.getName(), "test").getBody());

        int[] array = new Random().ints(100, -20, 60).toArray();
        for (int item : array) {
            Measurement measurement = new Measurement();
            measurement.setValue((double)item);
            Instant instant = Instant.now().minus(2, ChronoUnit.DAYS);
            instant.plus(Math.abs(item == 0 ? item + 10 : item), ChronoUnit.HOURS);
            measurement.setTimestamp(new Date());
            measurements.add(measurement);
        }
    }

    @Override
    public ResponseEntity<User> createUser(String userName, String password) {
        License license = licenses.stream()
                .filter(x -> x.getLicenseType() == LicenseType.Trail)
                .findFirst()
                .get();
        User user = new User(userName, password, license);
        users.add(user);
        UserLicense userLicense = new UserLicense();
        userLicense.setLicense(license);
        userLicense.setUser(user);
        userLicenses.add(userLicense);
        userLicense.setExpiryDate(new Date());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<License> createNewLicense(int requestsPerSeconds, LicenseType newLicenseType) {
        License license = new License(requestsPerSeconds, newLicenseType);
        licenses.add(license);
        return new ResponseEntity<>(license, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<License> changeLicense(String userName, LicenseType newLicenseType) {
        User user = getUserForName(userName);
        UserLicense userLicense = userLicenses.stream().filter(x -> x.getUser().equals(user)).findFirst().get();
        License license = userLicense.getLicense();
        license.setLicenseType(newLicenseType);
        return new ResponseEntity<>(license, HttpStatus.OK);
    }

    private User getUserForName(String userName) {
        return users.stream().filter(x -> userName.equals(x.getName())).findFirst().get();
    }

    @Override
    public ResponseEntity<Channel> createChannelForUser(String userName, String channelName) {
        User user = getUserForName(userName);
        UUID uuid = guidGenerator.generateNewChannelId();
        Channel channel = new Channel(uuid, channelName);
        user.addChannel(channel);
        channels.add(channel);
        return new ResponseEntity<>(channel, HttpStatus.CREATED);
    }

    @Override
    public Collection<Channel> listChannels(String userName) {
        User user = getUserForName(userName);
        return ImmutableList.copyOf(user.getChannels());
    }

    @Override
    public ResponseEntity<Channel> getChannel(String channelId) {
        Channel channel = channels.stream().filter(x -> channelId.equals(stringConverter.UUIDToString(x.getId()))).findFirst().get();
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }

    @Override
    public void deleteChannel(String channelId) {
        Channel channel = channels.stream().filter(x -> stringConverter.UUIDToString(x.getId()).equals(channelId)).findFirst().get();
        channels.remove(channel);
        User user = users.stream().filter(x -> x.getChannels().contains(channel)).findFirst().get();
        user.getChannels().remove(channel);
    }

    @Override
    public ResponseEntity<LicenseStatus> getLicenseStatus(String channelId) {
        Channel channel = channels.stream().filter(x -> stringConverter.UUIDToString(x.getId()).equals(channelId)).findFirst().get();
        User user = users.stream().filter(x -> x.getChannels().contains(channel)).findFirst().get();
        UserLicense userLicense = userLicenses.stream().filter(x -> x.getUser().equals(user)).findFirst().get();
        LicenseStatus licenseStatus = new LicenseStatus();
        licenseStatus.setExpired(userLicense.getExpiryDate().before(new Date()));
        licenseStatus.setExceedingTps(true);
        return new ResponseEntity<>(licenseStatus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<License> getLicenseForChannelId(String channelId) {
        Channel channel = channels.stream().filter(x -> stringConverter.UUIDToString(x.getId()).equals(channelId)).findFirst().get();
        User user = users.stream().filter(x -> x.getChannels().contains(channel)).findFirst().get();
        UserLicense userLicense = userLicenses.stream().filter(x -> x.getUser().equals(user)).findFirst().get();
        return new ResponseEntity<>(userLicense.getLicense(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> authenticateUser(String username, String password) {
        return null;
    }

    @Override
    public Collection<User> listUsers() {
        return users;
    }

    @Override
    public ResponseEntity<User> getUserByName(String userName) {
        return new ResponseEntity<>(getUserForName(userName), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Measurement>> getMeasurementForChannelId(String channelId) {
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }
}
