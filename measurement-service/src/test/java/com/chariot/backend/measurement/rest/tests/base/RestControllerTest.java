package com.chariot.backend.measurement.rest.tests.base;

import com.chariot.backend.measurement.service.MeasurementService;
import com.chariot.backend.measurement.service.tests.data.DataProvider;
import com.chariot.backend.measurement.rest.tests.utils.TestUtilities;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Dariusz Kijania on 7/1/2017.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {com.chariot.backend.measurement.config.TestConfiguration.class})
public class RestControllerTest  extends MeasurementComponentTest{

    @Autowired
    protected TestUtilities utilities;

    @Autowired
    protected DataProvider dataProvider;

    @MockBean
    protected MeasurementService measurementService;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
