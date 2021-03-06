package com.chariot.backend.gateway.webapp.rest;

import com.chariot.backend.model.*;
import com.chariot.backend.schema.login.LoginResponse;
import com.chariot.backend.schema.notification.NotificationRegistrationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface IWebAppRestClient {

    ResponseEntity<User> createUser(String userName, String password);

    ResponseEntity<License> changeLicense(String userName, LicenseType newLicenseType);

    ResponseEntity<Channel> createChannelForUser(String userName, String channelName);

    Collection<Channel> listChannels(String userName);

    ResponseEntity<Channel> getChannel(String channelId);

    void deleteChannel(String channelId);

    ResponseEntity<LicenseStatus> getLicenseStatus(String channelId);

    ResponseEntity<License> getLicenseForChannelId(String channelId);

    Collection<User> listUsers();

    ResponseEntity<User> getUserByName(String userName);

    ResponseEntity<List<Measurement>> getMeasurementForChannelId(String channelId);

    ResponseEntity<License> createNewLicense(int requestsPerSeconds, LicenseType newLicenseType);

    ResponseEntity<LoginResponse> authenticateUser(String username, String password);

    void putNewMeasurement(Double value, String channelId) throws Throwable;

    void registerNotificationToken(NotificationRegistrationToken notificationRegistrationToken);

    void setNewMeasurementNotificationRule(String channelId);

    void setOutOfBoundNotificationRule(String channelId, double upperBound, double lowerBound);
}
