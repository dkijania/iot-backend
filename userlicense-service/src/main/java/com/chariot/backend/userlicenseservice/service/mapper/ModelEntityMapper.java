package com.chariot.backend.userlicenseservice.service.mapper;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.License;
import com.chariot.backend.model.User;
import com.chariot.backend.model.UserLicense;
import com.chariot.backend.userlicenseservice.persist.ChannelEntity;
import com.chariot.backend.userlicenseservice.persist.LicenseEntity;
import com.chariot.backend.userlicenseservice.persist.UserEntity;
import com.chariot.backend.userlicenseservice.persist.UserLicenseEntity;
import com.chariot.backend.utils.uuid.UUIDStringConverter;
import org.apache.commons.codec.DecoderException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@Service
public class ModelEntityMapper {

    @Autowired
    private UUIDStringConverter uuidStringConverter;

    private final ModelMapper modelMapper;

    public ModelEntityMapper() {
        modelMapper = new ModelMapper();
    }

    public ChannelEntity toChannelEntity(Channel channel) {
        return modelMapper.map(channel, ChannelEntity.class);
    }

    public Channel toChannelModel(ChannelEntity channelEntity) {
        Channel channel = new Channel();
        try {
            channel.setId(uuidStringConverter.stringToUUID(channelEntity.getId()));
            channel.setName(channelEntity.getName());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
        return channel;
    }

    public List<Channel> toChannelModelList(List<ChannelEntity> inputList) {
        return inputList.stream()
                .map(this::toChannelModel)
                .collect(Collectors.toList());
    }

    public LicenseEntity toLicenseEntity(License license) {
        return modelMapper.map(license, LicenseEntity.class);
    }

    public License toLicenseModel(LicenseEntity license) {
        return modelMapper.map(license, License.class);
    }

    public UserEntity toUserEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public List<User> toUserModelList(List<UserEntity> inputList) {
        return inputList.stream()
                .map(this::toUser)
                .collect(Collectors.toList());
    }

    public User toUser(UserEntity user) {
        return modelMapper.map(user, User.class);
    }

    public UserLicense toUserLicense(UserLicenseEntity userLicenseEntity) {
        return modelMapper.map(userLicenseEntity, UserLicense.class);
    }
}
