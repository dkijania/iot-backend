package com.chariot.backend.userlicenseservice.service.license;

import com.chariot.backend.model.License;
import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.model.LicenseType;
import com.chariot.backend.model.UserLicense;
import com.chariot.backend.userlicenseservice.persist.*;
import com.chariot.backend.userlicenseservice.service.license.expiry.LicenseExpiryChecker;
import com.chariot.backend.userlicenseservice.service.mapper.ModelEntityMapper;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import com.chariot.backend.utils.uuid.UUIDStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@Service
public class LicenseApi implements ILicenseApi {

    @Autowired
    private ModelEntityMapper mapper;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UUIDStringConverter uuidStringConverter;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private UserLicenseRepositoryImpl userLicenseRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private LicenseExpiryChecker expiryChecker;


    @Override
    public License getLicenseForChannelId(String id) throws NamedEntityNotFoundException {
        UserLicenseEntity userLicenseEntity = getUserLicenseEntityForChannel(id);
        return mapper.toLicenseModel(userLicenseEntity.getLicense());
    }

    private UserEntity findUserForChannel(String id) throws NamedEntityNotFoundException {
        Optional<ChannelEntity> channelEntityOptional = channelRepository.findById(id).stream()
                .findFirst();
        return channelEntityOptional.orElseThrow(
                ()-> new NamedEntityNotFoundException(String.format("Channel not found for id {%s}",id),"Channel",id))
                .getUser();
    }

    @Override
    public License getLicenseForChannelId(UUID id) throws NamedEntityNotFoundException {
        return getLicenseForChannelId(uuidStringConverter.UUIDToString(id));
    }

    @Override
    public void changeLicenseTypeForUser(String userName, LicenseType newLicenseType)
            throws NamedEntityNotFoundException {
        UserEntity userEntity = userRepository.getByName(userName);
        UserLicenseEntity userLicenseEntity = userLicenseRepository.getByUser(userEntity);
        LicenseEntity licenseEntity = userLicenseEntity.getLicense();
        licenseEntity.setLicenseType(newLicenseType);
        userLicenseRepository.saveOrUpdate(userLicenseEntity);
    }

    @Override
    public void createNewLicense(License license) {
        LicenseEntity licenseEntity = mapper.toLicenseEntity(license);
        licenseRepository.save(licenseEntity);
    }

    @Override
    public LicenseStatus getLicenseStatus(String channelId) throws NamedEntityNotFoundException {
        UserLicenseEntity userLicenseEntity = getUserLicenseEntityForChannel(channelId);
        UserLicense userLicense = mapper.toUserLicense(userLicenseEntity);
        return expiryChecker.check(userLicense);
    }

    private UserLicenseEntity getUserLicenseEntityForChannel(String channelId) throws NamedEntityNotFoundException {
        UserEntity userEntity = findUserForChannel(channelId);
        return userLicenseRepository.getByUser(userEntity);
    }
}
