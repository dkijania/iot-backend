package com.chariot.backend.userlicenseservice.config;

import com.chariot.backend.userlicenseservice.persist.UserLoginLockRepository;
import com.chariot.backend.userlicenseservice.persist.UserRepository;
import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.chariot.backend.userlicenseservice")
@EnableWebMvc
public class TestConfiguration {

    @Bean
    @Primary
    UserRepository mockUserRepository() {
        return Mockito.mock(UserRepository.class);
    }


    @Bean
    @Primary
    UserLoginLockRepository mockUserLoginLockRepository() {
        return Mockito.mock(UserLoginLockRepository.class);
    }


    @Bean
    public CurrentDateTimeProvider currentDateTimeProvider() {
        return new CurrentDateTimeProvider();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
