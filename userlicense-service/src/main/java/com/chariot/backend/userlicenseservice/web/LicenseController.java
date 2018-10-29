package com.chariot.backend.userlicenseservice.web;

import com.chariot.backend.model.License;
import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.model.LicenseType;
import com.chariot.backend.schema.SuccessResponse;
import com.chariot.backend.userlicenseservice.service.license.ILicenseApi;
import com.chariot.backend.userlicenseservice.service.license.LicenseApiException;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import com.chariot.backend.utils.uuid.UUIDStringConverter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@RestController
@RequestMapping("/chariot/license")
@Cacheable
public class LicenseController {
    @Autowired
    private ILicenseApi licenseApi;

    @Autowired
    private UUIDStringConverter uuidStringConverter;

    @RequestMapping(path = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get License for channel id", response = License.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> getLicenseForChannelId(@RequestParam String channelId)
            throws NamedEntityNotFoundException, LicenseApiException {
        return ResponseEntity.ok(licenseApi.getLicenseForChannelId(channelId));
    }

    @RequestMapping(path = "/change",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Change License for user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<SuccessResponse> changeLicense(@RequestParam String userName, @RequestParam String newLicenseType)
            throws NamedEntityNotFoundException {
        licenseApi.changeLicenseTypeForUser(userName, LicenseType.fromText(newLicenseType));
        return new ResponseEntity<>(new SuccessResponse(String.format("License for user '%s' sucessfully upgraded to '%s'",
                userName, newLicenseType)), HttpStatus.OK);
    }


    @RequestMapping(path = "/create",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create new License")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void changeLicense(@RequestBody License license) {
        licenseApi.createNewLicense(license);
    }

    @RequestMapping(path = "/status",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Calculate License status")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LicenseStatus getLicenseStatus(@RequestParam String channelId) throws NamedEntityNotFoundException {
        return licenseApi.getLicenseStatus(channelId);
    }


}
