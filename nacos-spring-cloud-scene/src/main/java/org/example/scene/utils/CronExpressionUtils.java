package org.example.scene.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class CronExpressionUtils {
    private static final SimpleDateFormat sdfCron = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
    private static final SimpleDateFormat sdfString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String formatDateByPattern(Date date) {
        String formatTimeStr = null;
        if (Objects.nonNull(date)) {
            formatTimeStr = sdfCron.format(date);
        }
        return formatTimeStr;
    }
    public static String dateToCronExpression(String date) throws ParseException {

        Date parse = sdfString.parse(date);
        return formatDateByPattern(parse);
    }




}
//        //yyyy-MM-dd HH:mm
//        String cronExpression = "0 * * * * ?";
//        StringBuilder builder = new StringBuilder(cronExpression);
//        int hour1 = Integer.parseInt(date.substring(11,12));
//        int hour2 = Integer.parseInt(date.substring(11,13));
//        int minute1 = Integer.parseInt(date.substring(14,15));
//        int minute2 = Integer.parseInt(date.substring(14,16));
//        if (hour1==0){
//            builder.setCharAt(4,date.charAt(12));
//        }else {
//            builder.setCharAt(4,(char) hour2);
//        }
//        if (minute1==0){
//            builder.setCharAt(2,date.charAt(14));
//        }else {
//
//            builder.setCharAt(2,(char) minute2);
//        }
//
//        return builder.toString();
//    }

