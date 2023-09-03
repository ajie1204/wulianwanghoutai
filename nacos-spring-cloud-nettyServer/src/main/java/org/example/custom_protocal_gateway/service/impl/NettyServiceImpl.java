package org.example.custom_protocal_gateway.service.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.mqtt.*;
import org.apache.commons.lang3.StringUtils;
import org.example.custom_protocal_gateway.common.context.SpringContextUtil;
import org.example.custom_protocal_gateway.netty.channel.NettySocketHolder;
import org.example.custom_protocal_gateway.service.NettyService;
import org.example.custom_protocal_gateway.util.LogUtil;
import org.example.custom_protocal_gateway.util.TEA;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NettyServiceImpl implements NettyService {


    private TEA tea;
    byte[] keys = {0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x03, 0x00, 0x00, 0x00, 0x04};

    public NettyServiceImpl() {
        super();
        tea = SpringContextUtil.getBeanByClass(TEA.class);
    }


    @Override
    public void sendTCPCommand(String deviceId, String jsonCommand) {
//       /* CommandFrame frame = JsonUtil.jsonToObject(jsonCommand, CommandFrame.class);
//        String did = frame.getDeviceId();
//        String data = frame.getData();*/
//        ChannelHandlerContext channelHandlerContext = NettySocketHolder.get(deviceId);
//        channelHandlerContext.writeAndFlush(jsonCommand);
//        log.info("发送给设备的信息为"+jsonCommand);
    }


    @Override
    public void sendMQTTCommand(String deviceId, String jsonCommand) {
        try {
            if (StringUtils.isEmpty(deviceId) || StringUtils.isEmpty(jsonCommand)) {
                return;
            }
            NioSocketChannel nioSocket = NettySocketHolder.get(deviceId);
            if (nioSocket == null) {
                LogUtil.info("设备[" + deviceId + "]不存在或已离线!");
                return;
            }
            if (!nioSocket.isActive()) {
                LogUtil.info("设备[" + deviceId + "]已离线!");
//                redisService.appendOffLineMessage(deviceId,command.getId(),jsonCommand);
                return;
            }
            nioSocket.eventLoop().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] bytes = null;
                        try {
                            bytes = jsonCommand.getBytes("UTF-8");
                        } catch (Exception e) {
                            LogUtil.error(e);
                        }
                        if (bytes != null) {
                            int mid = 1;
                            try {
                                mid = (int) System.currentTimeMillis();
                            } catch (Exception e) {
                                LogUtil.error(e);
                            }
                            LogUtil.info("给设备【" + deviceId + "】发送消息" + jsonCommand);

                            //加密
                            byte[] bytes1 = tea.encrypt(bytes, keys);
                            sendQosMsg(nioSocket, "test", bytes1, mid);

                            sendQosMsg(nioSocket, "test", bytes, mid);

                        }
                    } catch (Exception e) {
                        LogUtil.error(e);
                    }
                }
            });
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    private boolean sendQosMsg(NioSocketChannel channel, String topic, byte[] byteBuf, int messageId) {
        try {

            //MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH,false, MqttQoS.AT_MOST_ONCE,false,0);
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_LEAST_ONCE, false, 0);
            MqttPublishVariableHeader mqttPublishVariableHeader = new MqttPublishVariableHeader(topic, messageId);
            MqttPublishMessage mqttPublishMessage = new MqttPublishMessage(mqttFixedHeader, mqttPublishVariableHeader, Unpooled.wrappedBuffer(byteBuf));
            channel.writeAndFlush(mqttPublishMessage);
            LogUtil.info("给设备发送消息(已发送)");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
//            CommandFrame commandFrame=null;
//            try {
//                commandFrame= JsonUtil.jsonToObject(jsonCommand, CommandFrame.class);
////                System.out.println(commandFrame.toString());
//                if(commandFrame==null) {
//                    LogUtil.info("设备["+deviceId+"]下发指令格式错误!");
//                    return;
//                }
//                if(StringUtils.isEmpty(commandFrame.getDeviceId())){
//                    LogUtil.info("设备["+deviceId+"]下发指令未指定设备ID!");
//                    return;
//                }
//                if(!deviceId.equals(commandFrame.getDeviceId())) {
//                    LogUtil.info("设备["+deviceId+"]下发指令设备ID与指令内容不符!");
//                    return;
//                }
//            }catch(Exception e) {
//                LogUtil.error(e);
//            }
