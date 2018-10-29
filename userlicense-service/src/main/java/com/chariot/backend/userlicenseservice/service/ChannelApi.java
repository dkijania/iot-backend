package com.chariot.backend.userlicenseservice.service;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.User;
import com.chariot.backend.userlicenseservice.persist.*;
import com.chariot.backend.userlicenseservice.service.mapper.ModelEntityMapper;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import com.chariot.backend.utils.uuid.UUIDProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dariusz Kijania on 7/23/2017.
 */
@Component
public class ChannelApi implements IChannelApi {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private ModelEntityMapper mapper;

    @Autowired
    private UUIDProvider uuidProvider;

    @Override
    public Channel createNewChannel(User user, String name) throws NamedEntityNotFoundException {
        UUID guid = uuidProvider.generateNewChannelId();
        Channel channel = new Channel(guid, name);
        UserEntity userEntity = userRepository.getByName(user.getName());
        ChannelEntity channelEntity = mapper.toChannelEntity(channel);
        channelEntity.setUser(userEntity);
        channelRepository.save(channelEntity);
        return channel;
    }

    @Override
    public List<Channel> getChannelByUser(String username) throws NamedEntityNotFoundException {
        UserEntity userEntity = userRepository.getByName(username);
        return  mapper.toChannelModelList(channelRepository.findByUser(userEntity));
    }

    @Override
    public String getChannelId(Channel channel) {
        return uuidProvider.UUIDToString(channel.getId());
    }

    public List<Channel> getAllChannels() {
        return mapper.toChannelModelList(channelRepository.findAll());
    }
}

