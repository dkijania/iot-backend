package com.chariot.backend.userlicense.status.persist;

import com.chariot.backend.model.RequestLog;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dariusz Kijania on 8/14/2017.
 */
@Transactional
public interface RequestLogRepository extends Repository<RequestLog, String> {

    void deleteByChannelId(String channelId);

    List<RequestLog> findByChannelId(String channelId);

    void save(RequestLog saved);
}