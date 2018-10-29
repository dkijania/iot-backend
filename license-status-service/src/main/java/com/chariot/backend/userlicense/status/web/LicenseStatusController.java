package com.chariot.backend.userlicense.status.web;

import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.userlicense.status.service.LicenseLimitVerifier;
import com.chariot.backend.utils.http.response.HttpCallFailed;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Dariusz Kijania on 8/7/2017.
 */

@RestController
@RequestMapping("/chariot/license/status")
public class LicenseStatusController {

    @Autowired
    private LicenseLimitVerifier licenseLimitVerifier;

    @RequestMapping(path = "/check",
            method = RequestMethod.GET)
    @ApiOperation(value = "Check limit for channel")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LicenseStatus getLicenseStatus(@RequestParam String channelId) throws IOException, HttpCallFailed {
        return licenseLimitVerifier.getLicenseStatus(channelId);
    }
}
