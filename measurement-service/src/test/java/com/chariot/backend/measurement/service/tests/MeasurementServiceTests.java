package com.chariot.backend.measurement.service.tests;

import com.chariot.backend.measurement.service.MeasurementService;
import com.chariot.backend.measurement.service.mapper.ModelEntityMapper;
import com.chariot.backend.measurement.service.tests.data.DataProvider;
import com.chariot.backend.model.Measurement;
import com.chariot.backend.measurement.persist.data.MeasurementEntity;
import com.chariot.backend.measurement.persist.repositories.MeasurementRepository;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Dariusz Kijania on 6/30/2017.
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {com.chariot.backend.measurement.config.TestConfiguration.class})
public class MeasurementServiceTests {

    private static final String RECEIVER_TOPIC = "hardware.t";

    @Autowired
    private MeasurementRepository repository;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private ModelEntityMapper mapper;

    @ClassRule
    public static final KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
    }

    @Test
    public void shouldSaveSingleMeasurement() {
        ArgumentCaptor<MeasurementEntity> argument = ArgumentCaptor.forClass(MeasurementEntity.class);
        Measurement inputMeasurement = dataProvider.getAnyModelMeasurement();

        measurementService.addMeasurement(inputMeasurement);

        Mockito.verify(repository).save(argument.capture());
        assertThat(argument.getValue()).isEqualTo(mapper.toMeasurementEntity(inputMeasurement));
    }
}

