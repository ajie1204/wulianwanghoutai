package org.example.custom_protocal_gateway.netty.channel;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.custom_protocal_gateway.service.KafkaService;
import org.example.custom_protocal_gateway.util.JsonUtil;
import org.example.custom_protocal_gateway.util.LogUtil;
import org.example.nacosspringcloudcommonentity.UplinkCommand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettySocketHolder {
	
	private static Map<String, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);
    
	public static Map<String, NioSocketChannel> getCHANNEL_MAP() {
        return CHANNEL_MAP;
    }
	
    public static void put(String id, NioSocketChannel socketChannel) {
//    	try {
//			NioSocketChannel oldChannel=get(id);
//    		if(oldChannel!=null) {
//    			oldChannel.close();
//    			remove(id);
//    		}
//    	}catch(Exception e) {
//    		LogUtil.error(e);
//    	}
    	try {
    		CHANNEL_MAP.put(id, socketChannel);
    	}catch(Exception e) {
    		LogUtil.error(e);
    	}
    }

    public static NioSocketChannel get(String id) {
        return CHANNEL_MAP.get(id);
    }


    
    public static void remove(String id) {
    	try {
    		CHANNEL_MAP.remove(id);
    	}catch(Exception e) {
    		LogUtil.error(e);
    	}
    }

    public static void remove(NioSocketChannel nioSocketChannel) {
    	try {
    		CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    	}catch(Exception e) {
    		LogUtil.error(e);
    	}
    }

	public static void remove(NioSocketChannel nioSocketChannel, KafkaService kafkaService) {
		try {
			CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> {
				String key=entry.getKey();
				CHANNEL_MAP.remove(key);
				String devId = key;
				UplinkCommand uplinkCommand = new UplinkCommand();
				uplinkCommand.setDeviceID(devId);
				//只有一种设备，暂时写死
				uplinkCommand.setSystem("Water");
				uplinkCommand.setUpload_code(219);
				uplinkCommand.setData(null);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Date date = new Date();
				String s = dateFormat.format(date);
				uplinkCommand.setTime(s);
				//转化为json格式
				String json = JsonUtil.objectToJson(uplinkCommand);
				kafkaService.upLink("Water",json);
			});
		}catch(Exception e) {
			LogUtil.error(e);
		}
	}


}
