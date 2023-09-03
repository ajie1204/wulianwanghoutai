package org.example.custom_protocal_gateway.netty.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.custom_protocal_gateway.bean.Command;
import org.example.custom_protocal_gateway.common.context.SpringContextUtil;
import org.example.custom_protocal_gateway.common.redis.RedisService;
import org.example.custom_protocal_gateway.netty.channel.NettySocketHolder;
import org.example.custom_protocal_gateway.service.KafkaService;
import org.example.custom_protocal_gateway.service.feign.DeviceManageService;
import org.example.custom_protocal_gateway.util.*;
import org.example.nacosspringcloudcommonentity.UplinkCommand;
import org.example.nacosspringcloudcommonentity.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;


@Slf4j
public class MqttServerHandler extends SimpleChannelInboundHandler<MqttMessage> {

    String url = "111.47.28.118:7348";

    private TEA tea;
    byte[] keys = {0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x03,0x00,0x00,0x00,0x04};
    private RedisService redisService;
    DeviceManageService deviceManageService;

    public MqttServerHandler(){
        super();
        redisService = SpringContextUtil.getBeanByClass(RedisService.class);
        tea = SpringContextUtil.getBeanByClass(TEA.class);
        deviceManageService= SpringContextUtil.getBeanByClass(DeviceManageService.class);

    }
    KafkaService kafkaService = SpringUtil.getBean(KafkaService.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try{
            ctx.writeAndFlush("Client Line succeed!");
            log.info("设备ip:"+ctx.channel().remoteAddress().toString()+"已连接");
            super.channelActive(ctx);
        }catch(Exception e){
            log.error("Line Server Exception！");
        }
    }
    /**
     * 接收到消息后处理
     *
     * @param ctx
     * @param mqttMessage
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,MqttMessage mqttMessage) throws Exception {
        MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
        if(ctx==null){
            log.warn("Channel disable");
            return;
        }
        if(mqttFixedHeader==null){
            log.warn("msg is null");
            return;
        }
        String name=mqttFixedHeader.messageType().name();
        LogUtil.info("接收到的消息类型："+name);

        switch (mqttFixedHeader.messageType()){
            //连接
            case CONNECT:
                MqttConnectMessage mqttConnectMsg=(MqttConnectMessage)mqttMessage;
                MqttConnectPayload payload = mqttConnectMsg.payload();
                String deviceId = payload.clientIdentifier();
                byte[] passwordInBytes = payload.passwordInBytes();
                LogUtil.info("连接设备ID："+deviceId);
//                LogUtil.info("连接密码："+passwordInBytes);
                String token=new String(passwordInBytes, CharsetUtil.UTF_8);
                LogUtil.info("token："+token);
                connectBack(ctx);
                ctx.channel().eventLoop().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (deviceManageService.getDeviceById(deviceId) != null){
//                            Boolean isToken =redisService.checkToken(token,deviceId);
                            Boolean isToken = true;
                            if (isToken){
                                //保存客户端与 Channel 之间的关系
                                NettySocketHolder.put(deviceId,(NioSocketChannel)ctx.channel());

//                                Set<Map.Entry<String, NioSocketChannel>> entries = NettySocketHolder.getCHANNEL_MAP().entrySet();
//                                for(Map.Entry m:entries){
//                                    System.out.println(m.getKey());
//                                }

                                Command tokenCommand=new Command();
                                tokenCommand.setId(deviceId);
                                tokenCommand.setS(CodeUtil.SUCCEED);

                               String tokenJson= JsonUtil.objectToJson(tokenCommand);


                                byte[] bytes=null;
                                try {
                                    bytes = tokenJson.getBytes("UTF-8");
                                } catch (Exception e) {
                                    LogUtil.error(e);
                                }
                                if(bytes!=null) {
                                    int mid=1;
                                    try {
                                        mid=(int)System.currentTimeMillis();
                                    }catch(Exception e) {
                                        LogUtil.error(e);
                                    }
                                    //加密
                                    /*byte[] bytes1 = tea.encrypt(bytes,keys);
                                    sendQos0Msg(ctx,"connectback",bytes1,mid);*/

                                    sendQos0Msg(ctx,"connectback",bytes,mid);
                                }
                            }else {
                                log.info("token无效");
                                //发送Token超时错误码
                                Command tokenCommand=new Command();
                                tokenCommand.setId(deviceId);
                                tokenCommand.setS(CodeUtil.TOKEN_ERROR);
                                tokenCommand.setUrl(url);
                                String tokenJson=JsonUtil.objectToJson(tokenCommand);

                                byte[] bytes=null;
                                try {
                                    bytes = tokenJson.getBytes("UTF-8");
                                } catch (Exception e) {
                                    LogUtil.error(e);
                                }
                                if(bytes!=null) {
                                    int mid=1;
                                    try {
                                        mid=(int)System.currentTimeMillis();
                                    }catch(Exception e) {
                                        LogUtil.error(e);
                                    }
                                    //加密
                                    /*byte[] bytes1 = tea.encrypt(bytes,keys);
                                    sendQos0Msg(ctx,"connectback",bytes1,mid);*/

                                    sendQos0Msg(ctx,"connectback",bytes,mid);
                                }
                                //关闭通道
                                closeCurrentChannel(ctx);
                            }
                        }else {
                            log.info("deviceId无效");
                            //发送deviceId无效错误码
                            Command tokenCommand=new Command();
                            tokenCommand.setId(deviceId);
                            tokenCommand.setS(CodeUtil.DEVICEId_ERROR);
                            tokenCommand.setUrl(url);
                            String tokenJson=JsonUtil.objectToJson(tokenCommand);

                            byte[] bytes=null;
                            try {
                                bytes = tokenJson.getBytes("UTF-8");
                            } catch (Exception e) {
                                LogUtil.error(e);
                            }
                            if(bytes!=null) {
                                int mid=1;
                                try {
                                    mid=(int)System.currentTimeMillis();
                                }catch(Exception e) {
                                    LogUtil.error(e);
                                }
                                //加密
                                /*byte[] bytes1 = tea.encrypt(bytes,keys);
                                sendQos0Msg(ctx,"connectback",bytes1,mid);*/

                                sendQos0Msg(ctx,"connectback",bytes,mid);
                            }
                            //关闭通道
                            closeCurrentChannel(ctx);
                        }

                    }

                });
                break;
            //订阅
            case SUBSCRIBE:
                subBack(ctx,(MqttSubscribeMessage)mqttMessage,1);
                break;
            //退订
            case UNSUBSCRIBE:
                int msgId=((MqttUnsubscribeMessage)mqttMessage).variableHeader().messageId();
                unSubBack(ctx,msgId);
                break;
            //发布
            case PUBLISH:
                MqttPublishMessage publishMsg=(MqttPublishMessage) mqttMessage;
                MqttPublishVariableHeader mqttPublishVariableHeader = publishMsg.variableHeader();
                int messageId = mqttPublishVariableHeader.messageId();
                ByteBuf payload2 = publishMsg.payload();
                byte[] bytes = CommonUtil.copyByteBuf(payload2);

                //解密
                /*byte[] bytes1 = tea.decrypt(bytes,keys);*/

                ctx.channel().eventLoop().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(bytes!=null) {
                                String data =new String(bytes,"UTF-8");
//                                log.info("收到的消息内容："+data);

//                                //转化为对象
//                                /*替换json中的空格和换行符，便于转换成对象*/
//                                data =data.replaceAll("\\s*", "");
//                                UplinkCommand uplinkCommand = JSON.parseObject(data, UplinkCommand.class);
//                                log.info("The date is:"+uplinkCommand);
                                /*设备响应及设备定时上传*/
                                JSONObject jsonObject = JSON.parseObject(data);
                                String system = (String) jsonObject.get("system");
                                log.info("收到上传消息"+data);
                                kafkaService.upLink(system,data);

                            }
                        }catch(Exception e) {
                            LogUtil.error(e);
                        }
                    }
                });

                sendPubBack(ctx,messageId);
                break;
            case PINGREQ:
//                LogUtil.info("接收到设备心跳包");
                pong(ctx);
                break;
            case PUBACK:
                MqttMessageIdVariableHeader messageIdVariableHeader = (MqttMessageIdVariableHeader) mqttMessage.variableHeader();
                int mid = messageIdVariableHeader.messageId();
                sendPubBack(ctx,mid);
                break;
            //客户端主动断开连接
            case DISCONNECT:
                log.info("客户端主动断开连接");
                break;
            default:
                break;
        }
    }


    private void connectBack(ChannelHandlerContext channel){
        try {
            MqttConnectReturnCode connectReturnCode = MqttConnectReturnCode.CONNECTION_ACCEPTED;
            MqttConnAckVariableHeader mqttConnAckVariableHeader = new MqttConnAckVariableHeader(connectReturnCode, false);
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(
                    MqttMessageType.CONNACK,false, MqttQoS.AT_MOST_ONCE, false, 0x02);
            MqttConnAckMessage connAck = new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
            channel.writeAndFlush(connAck);
        }catch(Exception e) {
            LogUtil.error(e.getMessage(), e);
        }

    }
    private void subBack(ChannelHandlerContext channel, MqttSubscribeMessage mqttSubscribeMessage, int num) {
        try {
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
            MqttMessageIdVariableHeader variableHeader = MqttMessageIdVariableHeader.from(mqttSubscribeMessage.variableHeader().messageId());
            List<Integer> grantedQoSLevels = new ArrayList<>(num);
            for (int i = 0; i < num; i++) {
                grantedQoSLevels.add(mqttSubscribeMessage.payload().topicSubscriptions().get(i).qualityOfService().value());
            }
            MqttSubAckPayload payload = new MqttSubAckPayload(grantedQoSLevels);
            MqttSubAckMessage mqttSubAckMessage = new MqttSubAckMessage(mqttFixedHeader, variableHeader, payload);
            channel.writeAndFlush(mqttSubAckMessage);

        }catch(Exception e) {
            LogUtil.error(e.getMessage(), e);
        }

    }
    /**
     * 回复取消订阅
     */
    private void unSubBack(ChannelHandlerContext channel, int messageId) {
        try {
            if(messageId<1) {
                messageId=1;
            }
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.UNSUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0x02);
            MqttMessageIdVariableHeader variableHeader = MqttMessageIdVariableHeader.from(messageId);
            MqttUnsubAckMessage mqttUnsubAckMessage = new MqttUnsubAckMessage(mqttFixedHeader, variableHeader);
            channel.writeAndFlush(mqttUnsubAckMessage);

        }catch(Exception e) {
            LogUtil.error(e.getMessage(), e);
        }

    }


    /**
     * 发送qos1 publish  确认消息
     */
    protected void sendPubBack(ChannelHandlerContext channel,int messageId){
        try{
            if(messageId<1) {
                messageId=1;
            }
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBACK,false, MqttQoS.AT_MOST_ONCE,false,0x02);
            MqttMessageIdVariableHeader from = MqttMessageIdVariableHeader.from(messageId);
            MqttPubAckMessage mqttPubAckMessage = new MqttPubAckMessage(mqttFixedHeader,from);
            channel.writeAndFlush(mqttPubAckMessage);
        }catch(Exception e) {
            LogUtil.error(e.getMessage(), e);
        }

    }


    /**
     * 回复pong消息
     */
    public void pong(ChannelHandlerContext channel) {
        try {
            MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0);
            channel.writeAndFlush(new MqttMessage(fixedHeader));
        }catch(Exception e) {
            LogUtil.error(e.getMessage(), e);
        }
    }

    /**
     * 关闭当前通道
     */
    private void closeCurrentChannel(ChannelHandlerContext ctx){
        LogUtil.info("设备通道已关闭！");
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                //发送离线队列
                NettySocketHolder.remove((NioSocketChannel)ctx.channel());

            }
        });
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



    public boolean sendQos0Msg(ChannelHandlerContext channel, String topic, byte[] byteBuf,int messageId){
        try {

            //MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH,false, MqttQoS.AT_MOST_ONCE,false,0);
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH,false, MqttQoS.AT_MOST_ONCE,false,0);
            MqttPublishVariableHeader mqttPublishVariableHeader = new MqttPublishVariableHeader(topic,messageId);
            MqttPublishMessage mqttPublishMessage = new MqttPublishMessage(mqttFixedHeader,mqttPublishVariableHeader,Unpooled.wrappedBuffer(byteBuf));
            channel.writeAndFlush(mqttPublishMessage);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
