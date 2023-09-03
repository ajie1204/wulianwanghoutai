package org.example.scene.utils;

import org.example.nacosspringcloudcommonentity.Task;

import java.util.Arrays;
import java.util.Calendar;

public class CronUtils {
    public static String createCronExpression(String time , String cycle) throws Exception{

        //拆分时间字符串 年，月，日，时，分，秒
        String[] split1 = time.split(" |-|:");

        StringBuffer cronExp = new StringBuffer("");

        if (cycle == null) {
            //秒
            cronExp.append(split1[5]).append(" ");
            //分
            cronExp.append(split1[4]).append(" ");
            //时
            cronExp.append(split1[3]).append(" ");
            //日
            cronExp.append(split1[2]).append(" ");
            //月份
            cronExp.append(split1[1]).append(" ");
            //周
            cronExp.append("?").append(" ");
            //年
            cronExp.append(split1[0]);
        }else {
            if (null != split1[5] && null != split1[4] && null != split1[3]) {
                //秒
                cronExp.append(split1[5]).append(" ");
                //分
                cronExp.append(split1[4]).append(" ");
                //时
                cronExp.append(split1[3]).append(" ");
                //日
                cronExp.append("?").append(" ");
                //月份
                cronExp.append("*").append(" ");
                //周
                cronExp.append(cycle);
            }
        }
        return cronExp.toString();
    }



}
