package org.example.custom_protocal_gateway.util;


import org.example.custom_protocal_gateway.config.BaseConfigurable;

/**
 * 类名 ReqDataUtils
 * 描述 TODO
 *
 * @author hedonglin
 * @version 1.0
 * @date 2019/9/29 16:33
 */

public class ReqDataUtils implements BaseConfigurable {
    /**
     * 方法描述:  判断字符串是不是Xml格式
     * @param msg 字符串
     * @author hedonglin
     * @date 2019/9/29 16:39
     * @return {@link boolean} true/false
    */
    public static boolean isXml(String msg){
        if (StringHelper.isBlank(msg)){
            return false;
        }

        if (msg.trim().endsWith(">")){
            return true;
        }
        return false;
    }


    /**
     * 方法描述:  判断字符串是不是Json格式
     * @param msg 字符串
     * @author hedonglin
     * @date 2019/9/29 16:39
     * @return {@link boolean} true/false
     */
    public static boolean isJson(String msg){
        if (StringHelper.isBlank(msg)){
            return false;
        }
        if (msg.trim().startsWith("{")
                && msg.trim().endsWith("}")){
            return true;
        }

        return false;
    }
    
}
