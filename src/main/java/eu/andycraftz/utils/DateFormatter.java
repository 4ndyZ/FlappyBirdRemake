package eu.andycraftz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * DateFormatter
 *
 * @version 1.0
 * @author AndyZ
 */
public class DateFormatter {

    private final SimpleDateFormat datum;

    /**
     * <b>DateFormatter</b>
     * <br>
     * Formats the date like "yyyy-MM-dd HH:mm:ss".
     * <br>
     */
    public DateFormatter() {
        this.datum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * <b>DateFormatter</b>
     * <br>
     * Formats the date in your prefered SimpleDate format.
     * <br>
     *
     * @param d Your SimpleDateFormat
     */
    public DateFormatter(SimpleDateFormat d) {
        this.datum = d;
    }

    /**
     * <b>DateFormatter</b>
     * <br>
     *
     * @param date as Date
     * @return Date as String
     */
    public String toString(Date date) {
        return datum.format(date);
    }

    /**
     * <b>DateFormatter</b>
     * <br>
     *
     * @param date as String
     * @return Date as Date
     * @throws ParseException
     */
    public Date fromString(String date) throws ParseException {
        return datum.parse(date);
    }

}
