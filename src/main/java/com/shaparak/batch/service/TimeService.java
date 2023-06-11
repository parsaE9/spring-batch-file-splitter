package com.shaparak.batch.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TimeService {


    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    public static String calculateDuration(Long millis) {
        Duration duration = Duration.ofMillis(millis);
        long HH = duration.toHours();
        long MM = duration.toMinutesPart();
        long SS = duration.toSecondsPart();
        long mm = duration.toMillisPart();
        return String.format("%02d:%02d:%02d.%03d", HH, MM, SS, mm);
    }


    public static String formatDateTime(Date dateTime) {
        return dateFormat.format(dateTime);
    }



}
