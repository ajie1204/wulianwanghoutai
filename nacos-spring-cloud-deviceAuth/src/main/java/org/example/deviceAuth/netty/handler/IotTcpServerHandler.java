package org.example.deviceAuth.netty.handler;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import org.example.deviceAuth.cache.DeviceCache;
import org.example.deviceAuth.common.context.SpringContextUtil;
import org.example.deviceAuth.common.redis.RedisService;
import org.example.deviceAuth.common.redis.base.CacheEnum;
import org.example.deviceAuth.entity.DeviceResponse;
import org.example.deviceAuth.entity.RegistInfo;
import org.example.deviceAuth.entity.RegistReply;
import org.example.deviceAuth.service.DeviceRegistService;

import org.example.deviceAuth.service.feign.DeviceManageService;
import org.example.deviceAuth.util.*;
import org.example.nacosspringcloudcommonentity.Device;


import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 消息逻辑处理
 * @author xxu
 *
 */
@Slf4j
public class IotTcpServerHandler extends SimpleChannelInboundHandler<String> {

	private DeviceRegistService deviceRegistService;
	private RedisService redisService;
	private TEA tea;
	private DeviceManageService deviceManageService;

	public IotTcpServerHandler(){
		super();
		deviceRegistService= SpringContextUtil.getBeanByClass(DeviceRegistService.class);
		redisService = SpringContextUtil.getBeanByClass(RedisService.class);
		tea = SpringContextUtil.getBeanByClass(TEA.class);
		deviceManageService = SpringContextUtil.getBeanByClass(DeviceManageService.class);

	}


    /**
     * 建立连接时触发，返回消息
     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		try{
//			LogUtil.info("Line Client Address:"+ctx.channel().remoteAddress());
//			ctx.writeAndFlush("Client Line succeed！");
//	        super.channelActive(ctx);
//		}catch(Exception e){
//			LogUtil.error("Line Server Exception！");
//		}
//    }





    /**
     * 收到消息时触发，返回消息
     */



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

		if (ctx == null) {
			LogUtil.warn("Channel disable");
			return;
		}
		if (msg == null) {
			LogUtil.warn("msg is null");
			return;
		}

		String response = null;

		//收到的String转JSONObject
		String str =msg.replaceAll("\n", "");
		JSONObject jsonObject = JSON.parseObject(str);

		if (jsonObject.containsKey("model")) {
			log.info("第一次收到数据为"+msg+"类型为："+msg.getClass().toString());
//			System.out.println("model");
//			System.out.println(jsonObject.get("protocol"));
			/*RegistInfo info = JSON.toJavaObject(jsonObject, RegistInfo.class);*/

			//收到的String转RegistInfo
			RegistInfo info = trasnsfer(str);
//			System.out.println(info.getAddress());

			ctx.channel().eventLoop().execute(new Runnable() {
				@Override
				public void run() {
					try {
						RegistReply reply = deviceRegistService.deviceRegist(info);
						String jsons = JsonUtil.objectToJson(reply);
						log.info("返回给设备的消息为："+jsons);
						//对发送信息进行加密发送
						byte[] keys = {0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x03,0x00,0x00,0x00,0x04};
						byte[] context = tea.encrypt(jsons.getBytes(),keys);
//						log.info("加密后"+context);
						ByteBuf replyBuf = ctx.alloc().buffer(context.length);
						replyBuf.writeBytes(context);
						ctx.writeAndFlush(replyBuf);

					} catch (Exception e) {
						log.error("给底层响应数据有误", e);

					} finally {
						closeCurrentChannel(ctx);
					}
				}
			});

		}
		/*设备应答帧*/
		if (jsonObject.containsKey("token")) {
			log.info("进入应答方法");
			log.info("收到数据为"+msg);
			DeviceResponse deviceResponse = JSON.parseObject(str, DeviceResponse.class);
			String deviceId = deviceResponse.getDeviceId();
			String token = deviceResponse.getToken();
			log.info(token);
			String token1 = DeviceCache.getTokenById(deviceId);
			log.info(token1);
			if (token.equals(token1)) {
				/*进入插入redis方法*/
				log.info("将token和deviceId存入redis");
				redisService.set(token, deviceId, CacheEnum.CACHE_DEVICE_TOKEN, 2L, TimeUnit.DAYS);
				Object o = redisService.get(token,CacheEnum.CACHE_DEVICE_TOKEN);
				log.info("value为"+o);
				DeviceCache.removeDevice(deviceId);
				if(deviceManageService.getDeviceById(deviceId) == null){
					Device device = new Device();
					device.setDeviceId(deviceId);
					deviceManageService.addDevice(device);
				}
			}



		}

		/*token过期后重新请求token*/
		if (jsonObject.containsKey("reason")) {
			log.info("重新请求token");
			log.info("收到数据为"+msg);
			String deviceId = (String) jsonObject.get("deviceId");
			/*String ciphertext = (String) jsonObject.get("ciphertext");*/
			System.out.println(deviceId);
			Device device = deviceManageService. getDeviceById(deviceId );
			System.out.println(device);
			/*验证deviceId和密文*/
			if (deviceManageService.getDeviceById(deviceId ).deviceId !=null) {
				ctx.channel().eventLoop().execute(new Runnable() {
					@Override
					public void run() {
						try {
							RegistReply reply = deviceRegistService.createNewToken(deviceId);
							String jsons = JsonUtil.objectToJson(reply);
							log.info("返回给设备的消息为："+jsons);
							//对发送信息进行加密发送
							byte[] keys = {0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x03,0x00,0x00,0x00,0x04};
							byte[] context = tea.encrypt(jsons.getBytes(),keys);
							ByteBuf replyBuf = ctx.alloc().buffer(context.length);
							replyBuf.writeBytes(context);
							ctx.writeAndFlush(replyBuf);
						} catch (Exception e) {
							log.error("给底层响应数据有误", e);

						} finally {
							closeCurrentChannel(ctx);
						}
					}
				});

			}


		}

	}

    /**
     * 关闭当前通道
     */
    private void closeCurrentChannel(ChannelHandlerContext ctx){
		try{
			ctx.close();
		}catch(Exception e){
			LogUtil.error(e.getMessage(), e);
		}
		try{
			ctx.channel().close();
		}catch(Exception e){
			LogUtil.error(e.getMessage(), e);
		}
    }


    /**
     * 超时处理
     * 如果N秒没有接受客户端的心跳，就触发;
     *
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
		LogUtil.error("设备连接超时,超过限制时间无心跳包=============");
		closeCurrentChannel(ctx);
    }


    /**
     * 所有断开连接最终触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		closeCurrentChannel(ctx);
		super.channelInactive(ctx);
    }




    /**
     * 异常断开触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	closeCurrentChannel(ctx);
    }


    /**
     * 消息接收完毕触发
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	super.channelReadComplete(ctx);
    }

    /**
     * 发送回复指令
     * @param ctx     返回
     * @param context 消息
     * @param
     */
    private void sendReplyInstruct(ChannelHandlerContext ctx, byte[] context) {
    		try{
        		LogUtil.info("send Reply Message Info:>>"+Arrays.toString(context));
	    	    	ByteBuf replyBuf = ctx.alloc().buffer(context.length);
	    	    	replyBuf.writeBytes(context);
	    	    	ctx.writeAndFlush(replyBuf);
	    	    	LogUtil.info("send Reply write over======");
    		}catch(Exception e){
    			LogUtil.error("send Reply write Exception",e);

    		}
    }


	/**
	 * 转换数据
	 */

	private RegistInfo trasnsfer(String msg){
		RegistInfo info = null;
		try {
			info = JSON.parseObject(msg, RegistInfo.class);
			System.out.println(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}





}
