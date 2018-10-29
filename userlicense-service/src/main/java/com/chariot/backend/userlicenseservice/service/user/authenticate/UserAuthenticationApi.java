package com.chariot.backend.userlicenseservice.service.user.authenticate;

import com.chariot.backend.userlicenseservice.persist.UserEntity;
import com.chariot.backend.userlicenseservice.persist.UserLoginLockEntity;
import com.chariot.backend.userlicenseservice.persist.UserLoginLockRepository;
import com.chariot.backend.userlicenseservice.persist.UserRepositoryImpl;
import com.chariot.backend.userlicenseservice.service.user.UserException;
import com.chariot.backend.utils.date.CurrentDateTimeProvider;
import com.chariot.backend.utils.error.exception.NamedEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserAuthenticationApi {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private UserLoginLockRepository userLoginLockRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CurrentDateTimeProvider currentDateTimeProvider;

    public boolean checkCredentials(String username, CharSequence password)
            throws UserException, NamedEntityNotFoundException {

        UserEntity userEntity = userRepository.getByName(username);
        UserLoginLockEntity userLoginLockEntity = userLoginLockRepository.findLastByUser(userEntity);

        if (firstLogin(userLoginLockEntity)) {
            return createFirstLoginRecord(userEntity, password);
        }
        if (userLoginLockEntity.isLocked() && !lockedPeriodIsOver(userLoginLockEntity)) {
            reportError(userEntity, userLoginLockEntity);
        }

        boolean comparePasswordsResult = comparePasswords(userEntity, password);
        if (!comparePasswordsResult) {
            userLoginLockEntity.setSuccessful(false);
            setLockedIfNumberOfTriesExceeded(userLoginLockEntity);
            updateNewIteration(userLoginLockEntity);
        } else {
            saveSucessfulLoginRecord(userLoginLockEntity);
        }
        userLoginLockRepository.save(userLoginLockEntity);
        return comparePasswordsResult;
    }

    private void saveSucessfulLoginRecord(UserLoginLockEntity userLoginLockEntity) {
        userLoginLockEntity.setLocked(false);
        userLoginLockEntity.setUnsuccessfulTry(0);
        userLoginLockEntity.setSuccessful(true);
        userLoginLockEntity.setNextLoginPossible(null);
        userLoginLockEntity.setTimeStamp(currentDateTimeProvider.getCurrentDate());
    }

    private void setLockedIfNumberOfTriesExceeded(UserLoginLockEntity userLoginLockEntity) {
        if (userLoginLockEntity.getUnsuccessfulTry() >= 3) {
            userLoginLockEntity.setLocked(true);
            userLoginLockEntity.setNextLoginPossible(calculateNextPossibleDate());
        }
    }

    private boolean firstLogin(UserLoginLockEntity userLoginLockEntity) {
        return userLoginLockEntity == null;
    }

    private boolean createFirstLoginRecord(UserEntity userEntity, CharSequence password) {
        boolean result = comparePasswords(userEntity, password);
        UserLoginLockEntity firstLoginRecord = new UserLoginLockEntity();
        firstLoginRecord.setTimeStamp(currentDateTimeProvider.getCurrentDate());
        if (result)
            firstLoginRecord.setSuccessful(true);
        else
            firstLoginRecord.setUnsuccessfulTry(1);
        firstLoginRecord.setLocked(false);
        firstLoginRecord.setUser(userEntity);
        userLoginLockRepository.save(firstLoginRecord);
        return result;

    }


    private void reportError(UserEntity userEntity, UserLoginLockEntity userLoginLockEntity) throws UserException {
        throw new UserException(String.format("User %s is locked. Next login possible at: %s",
                userEntity.getName(), new SimpleDateFormat().format(userLoginLockEntity.getNextLoginPossible())));
    }

    private void updateNewIteration(UserLoginLockEntity userLoginLockEntity) {
        userLoginLockEntity.setUnsuccessfulTry(userLoginLockEntity.getUnsuccessfulTry() + 1);
        userLoginLockEntity.setTimeStamp(currentDateTimeProvider.getCurrentDate());
    }

    public boolean comparePasswords(UserEntity userEntity, CharSequence password) {
        CharSequence expectedHashedPassword = userEntity.getPassword();
        CharSequence actualHashedPassword = passwordEncoder.encode(password);
        return expectedHashedPassword.equals(actualHashedPassword);
    }

    private boolean lockedPeriodIsOver(UserLoginLockEntity userLoginLockEntity) {
        Date nextLoginPossibleDate = userLoginLockEntity.getNextLoginPossible();
        Date currentDate = currentDateTimeProvider.getCurrentDate();
        return nextLoginPossibleDate.before(currentDate);
    }

    private Date calculateNextPossibleDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDateTimeProvider.getCurrentDate());
        calendar.add(Calendar.MINUTE, 10);
        return calendar.getTime();
    }

}
