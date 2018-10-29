package com.chariot.backend.userlicenseservice.persist;

import com.chariot.backend.model.Channel;
import com.chariot.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dariusz Kijania on 7/23/2017.
 */
public interface ChannelRepository extends JpaRepository<ChannelEntity,String> {

    void delete(ChannelEntity deleted);

    ChannelEntity save(ChannelEntity saved);

    List<ChannelEntity> findByName(String name);

    List<ChannelEntity> findById(String id);

    List<ChannelEntity> findAll();

    List<ChannelEntity> findByUser(UserEntity user);
}