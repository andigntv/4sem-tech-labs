package models;

import exceptions.TimeException;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * class for time speed up
 */
public class CustomDateTime {

    @Getter
    public static ZonedDateTime start = ZonedDateTime.now();
    @Getter
    public static ZonedDateTime now = start;

    public static void setNow(ZonedDateTime dateTime) throws TimeException {
        if (dateTime.compareTo(now) < 0)
            throw new TimeException("You can't go back in time");
        now = dateTime;
    }

}
