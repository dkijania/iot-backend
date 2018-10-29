package com.chariot.backend.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Nobody on 6/24/2017.
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan({"com.chariot.backend.notification","com.chariot.backend.utils"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
