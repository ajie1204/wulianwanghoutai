package org.example.nacosspringcloudcommonentity.util;

import com.alibaba.fastjson.JSON;
import org.example.nacosspringcloudcommonentity.DownCommand;

public class DataConvertUtils {


    /**
     * 原先Json格式的数据打包转换到message
     * @return
     */
    public static String getMessage(){

        DownCommand downCommand = new DownCommand();
        String message = JSON.toJSONString(downCommand);

        return message;
    }
}
