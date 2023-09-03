package org.example.nacosspringcloudcommonentity.util;

import java.util.Date;

public class TimeUtil {
    /**
     *
     * @param life 使用寿命
     * @param date1 该设备开始使用的时间
     * @param date2 当前时间
     * @return
     */
    public static Integer getDays(long life, Date date1,Date date2){
        long l1= life * 24 * 3600 * 1000;
        long l2 = date1.getTime()-date2.getTime();
        int remainTime = (int) ((l1+l2)/(24*3600*1000));
        if(remainTime<=0){
            return 0;
        }
        long n = remainTime%(24*3600);
        if (n==0){
            return remainTime;
        }else {
            return remainTime+1;
        }
    }

}
