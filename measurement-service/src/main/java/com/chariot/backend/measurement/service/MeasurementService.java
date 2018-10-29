package com.chariot.backend.measurement.service;

import com.chariot.backend.measurement.persist.repositories.MeasurementRepository;
import com.chariot.backend.measurement.service.mapper.ModelEntityMapper;
import com.chariot.backend.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Nobody on 6/25/2017.
 */
@Component
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private ModelEntityMapper mapper;

    public void addMeasurement(Date currentDate, String newValue, String channelId) {
        addMeasurement(new Measurement(currentDate, Float.parseFloat(newValue), channelId));
    }

    public void addMeasurement(Measurement measurement) {
        measurementRepository.save(mapper.toMeasurementEntity(measurement));
    }

    public List<Measurement> getAllMeasurements() {
        return mapper.toMeasurementModelList(measurementRepository.findAll());
    }

    public List<Measurement> getMeasurementsByChannel(String channelId) {
        return mapper.toMeasurementModelList(measurementRepository.findByChannelId(channelId));
    }
}
