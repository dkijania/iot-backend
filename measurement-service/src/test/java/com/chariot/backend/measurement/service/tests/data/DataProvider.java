package com.chariot.backend.measurement.service.tests.data;

import com.chariot.backend.measurement.persist.data.MeasurementEntity;
import com.chariot.backend.model.Measurement;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dariusz Kijania on 6/30/2017.
 */
@Component
public class DataProvider {

    public List<MeasurementEntity> getAnyEntityMeasurements() {
        List<MeasurementEntity> measurements = new LinkedList<>();
        MeasurementEntity measurement = getAnyEntityMeasurement();
        measurements.add(measurement);
        return measurements;
    }

    public MeasurementEntity getAnyEntityMeasurement() {
        return new MeasurementEntity(new Date(), 1.0f, "channel");
    }

    public Measurement getAnyModelMeasurement() {
        return new Measurement(new Date(), 1.0f, "channel");
    }

    public List<Measurement> getAnyModelMeasurements() {
        List<Measurement> measurements = new LinkedList<>();
        Measurement measurement = getAnyModelMeasurement();
        measurements.add(measurement);
        return measurements;
    }
}
