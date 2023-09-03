package org.example.deviceAuth.common.redis.base;

/**
 * 缓存类型枚举
 * @author Administrator
 *
 */
public enum CacheEnum {
	
	CACHE_SYS_CONFIG("配置缓存","SysConfig"),
	CACHE_DEVICE_TOKEN("设备Token","DeviceToken");

	
	
	private String groupName;
	private String groupCode;
	
	
	CacheEnum(String groupName,String groupCode) {
		this.groupName=groupName;
		this.groupCode=groupCode;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	

}
