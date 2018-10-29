package com.chariot.backend.measurement.persist.repositories;

import com.chariot.backend.measurement.persist.data.MeasurementEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Nobody on 6/24/2017.
 */
public interface MeasurementRepository extends MongoRepository<MeasurementEntity, String> {

    void delete(MeasurementEntity deleted);

    List<MeasurementEntity> findAll();

    MeasurementEntity save(MeasurementEntity saved);

    List<MeasurementEntity> findByChannelId(String channelId);
}