package com.chariot.backend.measurement.persist.tests;

import com.chariot.backend.measurement.persist.data.MeasurementEntity;
import com.chariot.backend.measurement.persist.repositories.MeasurementRepository;
import com.chariot.backend.measurement.persist.tests.config.MongoConfiguration;
import com.chariot.backend.measurement.service.tests.data.DataProvider;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dariusz Kijania on 6/30/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfiguration.class)
public class MeasurementRepositoryTest {

    @Autowired
    public DataProvider dataProvider;

    @Autowired
    public MeasurementRepository repository;


    @Test
    @UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
    public void shouldFindTheSameMeasurementAsInsertedTest() throws Exception {
        MeasurementEntity insertedMeasurement = dataProvider.getAnyEntityMeasurement();
        repository.save(insertedMeasurement);

        List<MeasurementEntity> measurementEntities = repository.findAll();
        assertEquals(measurementEntities.size(), 1);

        MeasurementEntity measurementEntity = measurementEntities.get(0);
        assertEquals(measurementEntity.getValue(), 1.0, 0.01);

    }

}
