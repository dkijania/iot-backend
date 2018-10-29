/*
package com.chariot.backend.userlicenseservice.config;

import com.chariot.backend.userlicenseservice.persist.UserEntity;
import com.chariot.backend.userlicenseservice.persist.UserLoginLockEntity;
import com.chariot.backend.userlicenseservice.persist.UserLoginLockRepository;
import com.chariot.backend.userlicenseservice.persist.UserRepository;
import com.chariot.backend.userlicenseservice.service.user.UserException;
import com.chariot.backend.userlicenseservice.service.user.authenticate.UserAuthenticationApi;
import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class UserAuthenticationTest {

    @Autowired
    UserRepository mockUserRepository;

    @Autowired
    UserLoginLockRepository mockUserLoginLockRepository;

    @Autowired
    UserAuthenticationApi userService;

    @Autowired
    CurrentDateTimeProvider currentDateTimeProvider;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void mustReturnUser() throws InterruptedException, UserException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("Dariusz");
        userEntity.setPassword("password");

        UserLoginLockEntity userLoginLockEntity = new UserLoginLockEntity();
        userLoginLockEntity.setId(1L);
        userLoginLockEntity.setUser(userEntity);
        userLoginLockEntity.setLocked(false);
        userLoginLockEntity.setTimeStamp(currentDateTimeProvider.getCurrentDate());
        userEntity.setName("Dariusz");
        userEntity.setPassword("password");

        when(mockUserRepository.findByName("Dariusz")).thenReturn(userEntity);
        when(mockUserLoginLockRepository.findLastByUser(userEntity)).thenReturn(userLoginLockEntity);

        assertTrue("Login Unsuccessful",
                userService.checkCredentials("Dariusz", "password") == true
        );
    }
}
*/
