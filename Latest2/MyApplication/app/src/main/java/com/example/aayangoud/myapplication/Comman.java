package com.example.aayangoud.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aayan Goud on 8/21/2016.
 */
public class Comman {

    public static int convertToInt(String value) {
        int result = 0;
        if (value.trim().length() > 0) {
            result = Integer.parseInt(value);
        } else {
            return result;
        }
        return result;
    }

    public static boolean isNullOrEmpty(String value) {
        boolean result = false;
        if (value != null && value.trim().length() > 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public static String calculateTimeDifference(String toTime, String fromTime) {
        String result = "00:00:00";
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(fromTime);
            Date date2 = format.parse(toTime);
            long diff = date2.getTime() - date1.getTime();
            result = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(diff),
                    TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)),
                    TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));

            //long difference = date2.getTime() - date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
