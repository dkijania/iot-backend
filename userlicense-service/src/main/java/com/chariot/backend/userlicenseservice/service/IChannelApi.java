package com.chariot.backend.userlicenseservice.service;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.User;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IChannelApi {

    Channel createNewChannel(User user, String name) throws NamedEntityNotFoundException;

    List<Channel> getAllChannels();

    List<Channel> getChannelByUser(String username) throws NamedEntityNotFoundException;

    String getChannelId(Channel channel);
}
