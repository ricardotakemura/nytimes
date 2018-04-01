package br.org.venturus.ricardotakemura.nytimes.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSS";
    private static final String US_FORMAT = "MM/dd/yyyy";
    private static final String APPEND_HOUR = " 00:00:01.0000";

    public static Date dateFromString(final String string) {
        try {
            final StringBuilder dateAsString = new StringBuilder(string);
            final int index = dateAsString.indexOf("T");
            if (index > 0) {
                dateAsString.delete(index, dateAsString.length());
            }
            dateAsString.append(APPEND_HOUR);
            final SimpleDateFormat dateFormat = new SimpleDateFormat(STANDARD_FORMAT, Locale.ROOT);
            return dateFormat.parse(dateAsString.toString());
        } catch (ParseException e) {
            Log.e("DateUtil","Error in parse", e);
            return null;
        }
    }

    public static String stringFromDate(final Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(US_FORMAT, Locale.ROOT);
        return dateFormat.format(date);
    }
}
