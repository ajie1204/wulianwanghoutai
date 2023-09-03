package org.example.custom_protocal_gateway.common.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.example.custom_protocal_gateway.bean.DeviceToken;
import org.example.custom_protocal_gateway.common.redis.base.CacheEnum;
import org.example.custom_protocal_gateway.common.redis.base.RedisGroup;
import org.example.custom_protocal_gateway.common.redis.base.RedisHash;
import org.example.custom_protocal_gateway.common.redis.base.RedisString;
import org.example.custom_protocal_gateway.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;


@Service
public class RedisService {
	
	@Autowired
    private RedisGroup redisGroup;
	
	@Autowired
	private RedisString redisString;
	
	@Autowired
	private RedisHash redisHash;

	@Autowired
	private RedisTemplate redisTemplate;


	/**
	 * 校验Token
	 * @param token
	 * @param deviceId
	 * @return
	 */
	public boolean checkToken(String token,String deviceId) {
//		redisGroup.set("123456",10000050,CacheEnum.CACHE_DEVICE_TOKEN);
		try {
			if(StringUtils.isEmpty(token) || StringUtils.isEmpty(deviceId)) {
				return false;
			}
			Object deviceTokenObj=redisGroup.get(token,CacheEnum.CACHE_DEVICE_TOKEN);
			if(deviceTokenObj==null) {
				return false;
			}
//			DeviceToken deviceToken=(DeviceToken)deviceTokenObj;
//			if(deviceId.equals(deviceToken.getDeviceId())) {
//				return true;
//			}
			if(deviceId.equals(deviceTokenObj.toString())) {
				return true;
			}
			return false;
		}catch(Exception e) {
			LogUtil.error(e);
			return false;
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}







	public void appendDevice(String devId,String channelInfo) {
		try {
			redisString.set(devId, channelInfo);
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}
	

	public void deleteDevice(String devId) {
		try {
			redisString.delKey(devId);
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}
	
	public void appendOffLineMessage(String devId,String msgId,String json) {
		try {
			redisHash.hSet(devId, msgId, json, 259200);//离线消息保持3天
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}
	
	public void deleteOffLineMessage(String devId,Object... item) {
		try {
			redisHash.hDel(devId, item);
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}
	
	public void deleteOffLineMessage(String devId) {
		try {
			redisHash.delKey(devId);
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}
	
	public List<String> queryOffLineMessage(String devId) {
		List<String> msgList=new ArrayList<String>();
		try {
			Map<Object, Object> maps=redisHash.hmGet(devId);
			if(maps!=null) {
		        for (Map.Entry<Object, Object> entry : maps.entrySet()) {
		        	Object obj=entry.getValue();
		        	if(obj!=null) {
		        		msgList.add(obj.toString());
		        	}
		        	
		        }
			}
		}catch(Exception e) {
			LogUtil.error(e);
		}
		return msgList;
	}
	

}
