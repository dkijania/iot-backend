package com.chariot.backend.gateway.webapp.web;

import com.chariot.backend.gateway.webapp.rest.IWebAppRestClient;
import com.chariot.backend.model.License;
import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.model.LicenseType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chariot/gateway/webapp/license")
public class LicenseController {

    @Autowired
    IWebAppRestClient remoteServiceCaller;

    @RequestMapping(path = "/create",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create new License")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<License> createNewLicense(@RequestParam int requestsPerSeconds, @RequestParam LicenseType newLicenseType) {
        return remoteServiceCaller.createNewLicense(requestsPerSeconds,newLicenseType);
    }

    @RequestMapping(path = "/change",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Change License for user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<License> changeLicense(@RequestParam String userName, @RequestParam LicenseType newLicenseType) {
        return remoteServiceCaller.changeLicense(userName,newLicenseType);
    }


    @RequestMapping(path = "/getByChannelId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get License for channel id", response = License.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<License> getLicenseForChannelId(@RequestParam String channelId) {
        return remoteServiceCaller.getLicenseForChannelId(channelId);
    }

    @RequestMapping(path = "/status",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Calculate License status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<LicenseStatus> getLicenseStatus(@RequestParam String channelId)
    {
        return remoteServiceCaller.getLicenseStatus(channelId);
    }


}
