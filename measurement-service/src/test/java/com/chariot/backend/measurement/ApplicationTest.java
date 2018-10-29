package com.chariot.backend.measurement;

import com.chariot.backend.measurement.config.TestConfiguration;
import com.chariot.backend.measurement.persist.data.MeasurementEntity;
import com.chariot.backend.measurement.persist.repositories.MeasurementRepository;
import com.chariot.backend.schema.MeasurementMessage;
import com.chariot.backend.utils.http.RestClientSafe;
import com.chariot.backend.utils.queue.KafkaMessageProducerMock;
import com.chariot.backend.utils.test.RestServiceTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ComponentScan({"com.chariot.backend.measurement", "com.chariot.backend.utils", "com.chariot.backend.schema"})
public class ApplicationTest {

    private KafkaMessageProducerMock kafkaMessageProducerMock;

    private static final String RECEIVER_TOPIC = "hardware.t";

    @ClassRule
    public static final KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

    @LocalServerPort
    protected int SUTPort;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    protected RestClientSafe restClient;

    @Autowired
    private RestServiceTestUtils restServiceTestUtils;

    @Autowired
    public MeasurementRepository measurementRepository;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
    }

    @Before
    public void setup() {
        Map<String, Object> senderProperties = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
        kafkaMessageProducerMock = new KafkaMessageProducerMock(senderProperties, RECEIVER_TOPIC);
    }

    @Test
    @UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
    public void testSaveMeasurementOnKafkaMessage() throws Exception {

        ArgumentCaptor<MeasurementEntity> argument = ArgumentCaptor.forClass(MeasurementEntity.class);
        MeasurementMessage message = new MeasurementMessage("", 6.0);
        kafkaMessageProducerMock.sendMessage(objectMapper.writeValueAsString(message));

        //nasty sleep to give consumer time to use measurement repository
        Thread.sleep(3 * 1000);

        Mockito.verify(measurementRepository).save(argument.capture());
        assertThat(argument.getValue().getValue()).isEqualTo(6.0f);
    }


    @Test
    @UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
    public void testErrorIsReturnedOnWrongMeasurementArgument() {

        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("newValue", "");

        ResponseEntity<String> responseEntity = restClient.post(uriParams,
                restServiceTestUtils.getSUTAddress(SUTPort) + "/chariot/measurement/new", String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
