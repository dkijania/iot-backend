package com.chariot.backend.utils.date;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dariusz Kijania on 8/14/2017.
 */
@Service
public class CurrentDateTimeProvider {

    public Date getCurrentDate()
    {
        return new Date();
    }

    public Date getDateInFuture(int daysToAdd) {
        Date dt = getCurrentDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, daysToAdd);
        return c.getTime();
    }
}
