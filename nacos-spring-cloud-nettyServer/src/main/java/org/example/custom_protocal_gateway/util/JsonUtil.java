package org.example.custom_protocal_gateway.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class JsonUtil {
	
	
	/**
	 * Object 转 Json 串
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj){
		try{
			if(obj==null){
				return "";
			}
			return JSONObject.toJSONString(obj);
		}catch(Exception e){
			LogUtil.error(e);
			return "";
		}
		
	}
	
	
	/**
	 * JSON字符串转换成对应的对象
	 * @param jsons
	 * @param clazz
	 * @return T
	 */
	public static <T> T jsonToObject(String jsons,Class<T> clazz){
		try{
			if(StringUtils.isEmpty(jsons)){
				return null;
			}
			return JSONObject.parseObject(jsons,clazz);
		}catch(Exception e){
			LogUtil.error(e);
			return null;
		}
	}
	
	/**
	 * JSON字符串转换成对应的List泛型
	 * @param jsons
	 * @param clazz
	 * @return List
	 * @throws Exception
	 */
	public static <T> List<T>  jsonToList(String jsons,Class<T> clazz){
		try{
			if(StringUtils.isEmpty(jsons)){
				return null;
			}
			return JSON.parseArray(jsons,clazz); 
		}catch(Exception e){
			LogUtil.error(e);
			return null;
		}

	}

	
}
