package org.example.custom_protocal_gateway.common.redis.base;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.example.custom_protocal_gateway.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RedisGroup {

	@Resource
    private RedisTemplate redisTemplate;
	private final String GROUP_MARK=":";
	
	
	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value,CacheEnum cache) {
		boolean result = false;
		try {
			redisTemplate.boundValueOps(cache.getGroupCode()+GROUP_MARK+key).set(value);
			result = true;
		} catch (Exception e) {
			LogUtil.error(e);
		}
		return result;
	}
	

	/**
	 * 写入缓存设置时效时间，单位秒
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setByTime(final String key, Object value, long expireTime,CacheEnum cache) {
		boolean result = false;
		try {
			redisTemplate.boundValueOps(cache.getGroupCode()+GROUP_MARK+key).set(value,expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			LogUtil.error(e);
		}
		return result;
	}
	
	/**
	 * 写入缓存设置时效时间，单位天
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setByLongTime(final String key, Object value, long expireTime,CacheEnum cache) {
		boolean result = false;
		try {
			redisTemplate.boundValueOps(cache.getGroupCode()+GROUP_MARK+key).set(value,expireTime, TimeUnit.DAYS);
			result = true;
		} catch (Exception e) {
			LogUtil.error(e);
		}
		return result;
	}
	
	
	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(CacheEnum cache,final String... keys) {
		try {
			for (String key : keys) {
				remove(key,cache);
			}
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(String pattern) {
		try {
			Set<Serializable> keys = redisTemplate.keys(pattern);
			if (keys.size() > 0) {
				redisTemplate.delete(keys);
			}		
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key,CacheEnum cache) {
		try{
			redisTemplate.delete(cache.getGroupCode()+GROUP_MARK+key);
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key,CacheEnum cache) {
		try {
			return redisTemplate.hasKey(cache.getGroupCode()+GROUP_MARK+key);
		}catch(Exception e) {
			LogUtil.error(e);
		}
		return false;
	}

	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key,CacheEnum cache) {
		try {
	        BoundValueOperations<Serializable, Object> valueOper = redisTemplate.boundValueOps(cache.getGroupCode()+GROUP_MARK+key);
	        return valueOper.get();
		}catch(Exception e) {
			LogUtil.error(e);
		}
		return null;
	}






}
