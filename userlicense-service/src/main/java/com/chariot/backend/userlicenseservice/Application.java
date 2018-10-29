package com.chariot.backend.userlicenseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Dariusz Kijania on 7/26/2017.
 */
@ComponentScan({"com.chariot.backend.userlicenseservice",
        "com.chariot.backend.utils"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chariot.backend.userlicenseservice")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
