package com.chariot.backend.measurement.config;

import com.chariot.backend.utils.mongodb.converters.InstantToLongConverter;
import com.chariot.backend.utils.mongodb.converters.LocalDateToStringConverter;
import com.chariot.backend.utils.mongodb.converters.LongToInstantConverter;
import com.chariot.backend.utils.mongodb.converters.StringToLocalDateConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

 /* Created by Nobody on 6/24/2017.
  */
@PropertySource(value={"classpath:application.properties"})
@Configuration
@EnableMongoRepositories(basePackages = "com.chariot.backend.measurement.persist")
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private int port;

    @Override
    public String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

    @Override
    public String toString() {
        return String.format("MongoDbConfig: %s:%s/%s", dbName, host, port);
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                new InstantToLongConverter(), new LongToInstantConverter(),
                new LocalDateToStringConverter(), new StringToLocalDateConverter()));
    }

}