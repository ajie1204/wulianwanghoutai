package org.example.deviceAuth.service;


import org.example.deviceAuth.entity.RegistInfo;
import org.example.deviceAuth.entity.RegistReply;

public interface DeviceRegistService {
	
	public RegistReply deviceRegist(RegistInfo info) throws Exception;

	public RegistReply createNewToken(String deviceId);
	

}
