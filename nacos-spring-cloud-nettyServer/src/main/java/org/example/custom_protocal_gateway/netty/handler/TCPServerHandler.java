package org.example.custom_protocal_gateway.netty.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.custom_protocal_gateway.netty.channel.NettySocketHolder;
import org.example.custom_protocal_gateway.service.KafkaService;
import org.example.custom_protocal_gateway.util.CommandUtil;
import org.example.custom_protocal_gateway.util.LogUtil;
import org.example.custom_protocal_gateway.util.NumberUtil;
import org.example.nacosspringcloudcommonentity.UplinkCommand;
import org.example.nacosspringcloudcommonentity.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class

TCPServerHandler extends SimpleChannelInboundHandler<String> {


    KafkaService kafkaService = SpringUtil.getBean(KafkaService.class);

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try{
            //LogUtil.info("Line Client Address:"+ctx.channel().remoteAddress());
            //ctx.channel().read();
            ctx.writeAndFlush("Client Line succeed!");
            log.info("设备ip:"+ctx.channel().remoteAddress().toString()+"已连接");
            super.channelActive(ctx);
        }catch(Exception e){
            logger.error("Line Server Exception！");
        }

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


        try {
            if(ctx==null){
                    logger.warn("Channel disable");
                    return;
                }
            if(msg==null) {
                logger.warn("message is null");
                return;
            }
            /*替换json中的空格和换行符，便于转换成对象\s*   */
            msg =msg.replaceAll("\n", "");
            if (msg.contains("upload_code")){
                UplinkCommand command = JSON.parseObject(msg, UplinkCommand.class);
            /*log.info("command为"+command);
            log.info("command的属性值"+command.getSystem()+"+"+command.getDeviceID());*/
                /*设备连接*/
                if (command.getUpload_code().equals(0)){
                    NettySocketHolder.put(command.getDeviceID(), (NioSocketChannel)ctx.channel());
                    return;
                }
            }

            /*设备响应及设备定时上传*/
            JSONObject jsonObject = JSON.parseObject(msg);
            String system = (String) jsonObject.get("system");
            log.info("收到上传消息"+msg);
            kafkaService.upLink(system,msg);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void onLine(ChannelHandlerContext ctx,String devId) {
        //0.回包
        //1.验证Token
        //2.设置心跳检测
        //3.存储内存管道
        //4.发送上线请求队列
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                delonLine(ctx,devId);
            }
        });
    }

    private void delonLine(ChannelHandlerContext ctx,String devId) {
        try {
            //保存客户端与 Channel 之间的关系
            NettySocketHolder.put(devId,(NioSocketChannel)ctx.channel());
            //推送上线通知
            /*nettyCommandService.sendDeviceState(devId, 1);*/
            //消费离线消息
            /* nettyCommandService.sendOffLineMessage(devId);*/
        } catch (Exception e) {
            LogUtil.error(e);
            closeCurrentChannel(ctx);
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
                NettySocketHolder.remove((NioSocketChannel)ctx.channel(),kafkaService);
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
}
