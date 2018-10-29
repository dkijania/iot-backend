package com.chariot.backend.measurement.rest.tests;

import java.util.List;

import com.chariot.backend.model.Measurement;
import com.chariot.backend.measurement.rest.tests.base.RestControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Dariusz Kijania on 7/1/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MeasurementControllerTest extends RestControllerTest{

    @Test
    public void shouldReturnAllMeasurements() throws Exception {
        final String channelName = "channel";

        List<Measurement> expectedLists = dataProvider.getAnyModelMeasurements();
        when(measurementService.getMeasurementsByChannel("channel")).thenReturn(expectedLists);
        String expectedJson = utilities.toJson(expectedLists);

        MvcResult result = mvc.perform(get("/chariot/measurement/get")
                .param("channelId",channelName)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedJson);
    }
}
