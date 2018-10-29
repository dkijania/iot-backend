import com.chariot.backend.gateway.hardware.Application;
import com.chariot.backend.model.LicenseStatus;
import com.chariot.backend.utils.http.RestClient;
import com.chariot.backend.utils.queue.KafkaConsumerMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@RunWith(SpringRunner.class)
public class ComponentTest {

    private static final String HELLOWORLD_TOPIC = "hardware.t";
    private RestClient restClient;
    private KafkaConsumerMock kafkaConsumerMock;

    @Value("${checkLicenseEndpoint}")
    private String checkLicenseEndpoint;

    @LocalServerPort
    private int port;

    @ClassRule
    public static final KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, HELLOWORLD_TOPIC);

    @Before
    public void before() {
        System.setProperty("spring.kafka.bootstrapServers", kafkaEmbedded.getBrokersAsString());

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("sampleRawConsumer", "false", kafkaEmbedded);
        consumerProps.put("auto.offset.reset", "earliest");

        kafkaConsumerMock = new KafkaConsumerMock(consumerProps, HELLOWORLD_TOPIC);
        restClient = new RestClient();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public WireMockRule service = new WireMockRule(9090);


    @Test
    public void testReceive() throws Exception {

        String channelId = "channel";
        String value = "6.0";

        initMockForLicenseStatusService(channelId);
        ResponseEntity response = sendNewHardwareMeasurement(channelId, value);
        kafkaConsumerMock.subscribe();

        String kafkaMessageAtConsumer = kafkaConsumerMock.getLastMessage();

        assertThat(kafkaMessageAtConsumer).isEqualTo(formatResponse(channelId,value));
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private String formatResponse(String channelId, String value) {
        return String.format("{\"channelId\":\"%s\",\"value\":%s}", channelId, value);
    }

    private void initMockForLicenseStatusService(String channelId) throws JsonProcessingException {
        String detailsString =
                objectMapper.writeValueAsString(new LicenseStatus(false, "", false));

        stubFor(get(urlEqualTo("/check?channelId=" + channelId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(detailsString)));
    }

    private ResponseEntity<String> sendNewHardwareMeasurement(String channelId, String value) {
        MultiValueMap<String, String> uriParams = new LinkedMultiValueMap<>();
        uriParams.add("value", value);
        uriParams.add("channelId", channelId);

        return restClient.post(uriParams, "http://localhost:" + port + "/chariot/gateway/hardware/newValue");

    }

}
