package com.chariot.backend.userlicenseservice.config;

import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CurrentDateTimeProvider currentDateTimeProvider() {
        return new CurrentDateTimeProvider();
    }
}
