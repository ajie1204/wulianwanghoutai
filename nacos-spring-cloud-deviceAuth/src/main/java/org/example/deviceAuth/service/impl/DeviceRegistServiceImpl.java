package org.example.deviceAuth.service.impl;



import org.apache.commons.fileupload.FileUploadException;
import org.example.deviceAuth.cache.DeviceCache;
import org.example.deviceAuth.common.net.util.CodeUtil;

import org.example.deviceAuth.common.net.util.Uuid;
import org.example.deviceAuth.common.redis.RedisService;
import org.example.deviceAuth.entity.DeviceToken;
import org.example.deviceAuth.entity.RegistInfo;
import org.example.deviceAuth.entity.RegistReply;
import org.example.deviceAuth.service.DeviceRegistService;
import org.example.deviceAuth.service.feign.DeviceManageService;

import org.example.deviceAuth.util.TokenUtil;
import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DeviceRegistServiceImpl implements DeviceRegistService {
	
	@Autowired
    private RedisService redisService;

	@Autowired
	DeviceManageService deviceManageService;


	@Value("111.47.28.118:7349")
	String tcpUrl;

//	@Value("${tcp.port}")
//	String tcpPort;


	
	@Override
	public RegistReply deviceRegist(RegistInfo info) throws Exception{


		/*生成设备id*/
		RegistReply reply = new RegistReply();

		String s = deviceManageService.checkDevice();
		int i = Integer.valueOf(s).intValue();
		String str = "00000000";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(str);
		stringBuilder.replace(8-s.length(),8,s);
		String deviceId = stringBuilder.toString();
		Device device = new Device();
		device.setDeviceId(String.valueOf(i+1));
		deviceManageService.updataCheckDevice(device);



		//2.生成Token写入Cache缓存
		String token= TokenUtil.createUUID();
		DeviceToken deviceToken=new DeviceToken();
		deviceToken.setDevId(deviceId);

		DeviceCache.add(deviceId,token);


		//3.返回设备需要的信息
		reply.setDeviceID(deviceId);
		reply.setToken(token);
		if (info.getProtocol().equals("tcp")) {
				reply.setUrl(tcpUrl);
				reply.setProtocol("tcp");
				/*放入密文*/


		}

		return reply;
	}

	@Override
	public RegistReply createNewToken(String deviceId) {
		RegistReply reply = new RegistReply();
		String token = TokenUtil.createUUID();
		DeviceToken deviceToken = new DeviceToken();
		deviceToken.setDevId(deviceId);


		Device device = deviceManageService.getDeviceById(deviceId);

		DeviceCache.add(deviceId, token);
		reply.setDeviceID(deviceId);
		reply.setToken(token);
		/*查找设备的协议*/

		if (device.protocol.equals("tcp")) {
			reply.setUrl(tcpUrl);
			reply.setProtocol("tcp");
//		reply.setPort(tcpPort);
	}


			return reply;
		}

	}
