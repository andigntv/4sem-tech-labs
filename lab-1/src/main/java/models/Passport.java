package models;

import exceptions.PassportException;
import lombok.Value;

/**
 * class for passport
 */
@Value
public class Passport {
    int SeriesLength = 4;
    int NumberLength = 6;
    String series;
    String number;

    public Passport(String series, String number) throws PassportException {
        if(series.isEmpty())
            throw new IllegalArgumentException("Passport series cannot be empty");
        if (number.isEmpty())
            throw new IllegalArgumentException("Passport number cannot be empty");

        if (series.length() != SeriesLength)
            throw new PassportException("Invalid lenght of passport series");
        if (number.length() != NumberLength)
            throw new PassportException("Invalid lenght of passport number");

        this.series = series;
        this.number = number;
    }
}
