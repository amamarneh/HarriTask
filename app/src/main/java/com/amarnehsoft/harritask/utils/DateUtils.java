package com.amarnehsoft.harritask.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static boolean isToday(String strDate) {
        return android.text.format.DateUtils.isToday(getTimeInMilliSecondsFromStringDate(strDate));
    }

    public static boolean isToday(long timeInMilliSeconds) {
        return android.text.format.DateUtils.isToday(timeInMilliSeconds);
    }

    public static long getTimeInMilliSecondsFromStringDate(String strDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate != null ? convertedDate.getTime() : 0;
    }

    public static boolean isTomorrow(String strDate){
        return isTomorrow(getTimeInMilliSecondsFromStringDate(strDate));
    }

    public static boolean isTomorrow(long timeInMilliSeconds){
        Calendar startOfTomorrow = Calendar.getInstance();
        startOfTomorrow.add(Calendar.DAY_OF_YEAR,1);
        startOfTomorrow.set(Calendar.HOUR_OF_DAY,0);
        startOfTomorrow.set(Calendar.MINUTE, 0);
        startOfTomorrow.set(Calendar.SECOND, 0);

        Calendar endOfTomorrow = Calendar.getInstance();
        endOfTomorrow.add(Calendar.DAY_OF_YEAR,1);
        endOfTomorrow.set(Calendar.HOUR_OF_DAY,23);
        endOfTomorrow.set(Calendar.MINUTE, 59);
        endOfTomorrow.set(Calendar.SECOND, 59);

        return timeInMilliSeconds > startOfTomorrow.getTimeInMillis() && timeInMilliSeconds < endOfTomorrow.getTimeInMillis();
    }

    public static String format(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d/MM/yyyy", Locale.getDefault());
        return dateFormat.format(new Date(date));
    }
}
