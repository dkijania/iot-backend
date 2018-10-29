package com.chariot.backend.measurement.persist.tests.config;

import com.chariot.backend.measurement.persist.repositories.MeasurementRepository;
import com.chariot.backend.measurement.service.tests.data.DataProvider;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MeasurementRepository.class)
public class MongoConfiguration {

    @Bean
    public Mongo mongo() {
        // Configure a Fongo instance
        return new Fongo("mongo-test").getMongo();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "collection-name");
    }

    @Bean
    public DataProvider dataProvider() {
        return new DataProvider();
    }
}

