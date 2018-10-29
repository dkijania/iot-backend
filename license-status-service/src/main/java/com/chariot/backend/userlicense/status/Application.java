package com.chariot.backend.userlicense.status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Dariusz Kijania on 8/12/2017.
 */
@ComponentScan({"com.chariot.backend.userlicense.status","com.chariot.backend.utils"})
@EnableSwagger2
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}