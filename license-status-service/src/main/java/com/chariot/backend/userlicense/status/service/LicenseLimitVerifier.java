package com.chariot.backend.userlicense.status.service;

import com.chariot.backend.model.License;
import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.model.RequestLog;
import com.chariot.backend.userlicense.status.config.Configuration;
import com.chariot.backend.userlicense.status.persist.RequestLogRepository;
import com.chariot.backend.userlicense.status.rest.LicenseProvider;
import com.chariot.backend.userlicense.status.rest.LicenseStatusProvider;
import com.chariot.backend.utils.http.response.HttpCallFailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by Dariusz Kijania on 8/19/2017.
 */
@Service
public class LicenseLimitVerifier {

    private static final int TIME_INTERVAL_IN_SECONDS = 10;
    //Default value = 2
    private int numberOfMessagesAllowed = 2;
    private Instant last_check = Instant.now();

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Autowired
    private Configuration configuration;

    @Autowired
    private LicenseProvider licenseProvider;

    @Autowired
    private LicenseStatusProvider licenseStatusProvider;

    private void setTpsLimit(ResponseEntity<License> licenseResponseEntity) throws HttpCallFailed {
        License license = licenseResponseEntity.getBody();
        numberOfMessagesAllowed = license.getRequestsPerSeconds();
    }

    public void persistRequestLogForChannel(String channelId) {
        RequestLog requestLog = new RequestLog();
        requestLog.setChannelId(channelId);
        requestLogRepository.save(requestLog);
    }

    public LicenseStatus getLicenseStatus(String channelId) throws HttpCallFailed {

        ResponseEntity<License> licenseResponse = licenseProvider.getLicenseFromUserLicenseService(channelId);
        setTpsLimit(licenseResponse);

        ResponseEntity<LicenseStatus> licenseStatusResponse =
                licenseStatusProvider.getLicenseStatusFromUserLicenseService(channelId);
        LicenseStatus licenseStatus = licenseStatusResponse.getBody();

        if (licenseStatus.isExpired()) {
            return licenseStatus;
        }

        Instant currentTime = Instant.now();
        long distance = Duration.between(last_check, currentTime).get(ChronoUnit.SECONDS);

        if (distance > TIME_INTERVAL_IN_SECONDS) {
            requestLogRepository.deleteByChannelId(channelId);
            persistRequestLogForChannel(channelId);
            last_check = currentTime;
            licenseStatus.setExceedingTps(false);
            return licenseStatus;
        } else {
            int counter = requestLogRepository.findByChannelId(channelId).size();
            if (counter >= numberOfMessagesAllowed) {

                licenseStatus.setExceedingTps(true);
                return licenseStatus;
            } else {
                persistRequestLogForChannel(channelId);
                last_check = currentTime;
                licenseStatus.setExceedingTps(false);
                return licenseStatus;
            }
        }
    }
}
