package com.chariot.backend.measurement.config;

import com.chariot.backend.measurement.service.tests.data.DataProvider;
import com.chariot.backend.measurement.persist.repositories.MeasurementRepository;
import com.chariot.backend.measurement.rest.tests.utils.TestUtilities;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by Dariusz Kijania on 6/30/2017.
 */
@ComponentScan("com.chariot.backend.measurement")
@EnableWebMvc
public class TestConfiguration extends AbstractMongoConfiguration {
    @Bean
    @Primary
    public MeasurementRepository nameService() {
        return Mockito.mock(MeasurementRepository.class);
    }

    public static final String DATABASE_NAME = "demo-test";

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Bean
    public Mongo mongo() {
        Fongo queued = new Fongo("mockDb");
        return queued.getMongo();
    }

    @Bean
    public DataProvider dataProvider() {
        return new DataProvider();
    }

    @Bean
    public TestUtilities utilities() {
        return new TestUtilities();
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }
}
