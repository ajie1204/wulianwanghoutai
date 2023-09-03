package org.example.deviceAuth.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.deviceAuth.common.context.SpringContextUtil;
import org.example.deviceAuth.common.redis.RedisService;
import org.example.deviceAuth.service.DeviceRegistService;
import org.example.deviceAuth.util.ByteUtil;
import org.example.deviceAuth.util.JsonUtil;
import org.example.deviceAuth.util.TEA;
//import org.example.deviceAuth.util.TeaUtil;
import com.alibaba.fastjson.JSONObject;


import java.util.List;
//针对tea解密的解码器

@Slf4j
public class TcpHandler extends ByteToMessageDecoder {
    private TEA tea;

    public TcpHandler() {
        super();
        tea = SpringContextUtil.getBeanByClass(TEA.class);
    }


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object decoded = this.decode(channelHandlerContext, byteBuf);
        if (decoded != null) {
            list.add(decoded);
        }
    }
    @SneakyThrows
    private Object decode(ChannelHandlerContext ctx, ByteBuf in) {
        byte[] keys = {0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x02,0x00,0x00,0x00,0x03,0x00,0x00,0x00,0x04};
////测试
//        JSONObject jsonObj = new JSONObject(true);
//        jsonObj.put("producer", "1");
//        jsonObj.put("time", "2022/5/12 22:00:00");
//        jsonObj.put("address", "1");
//        jsonObj.put("batch", "1");
//        jsonObj.put("type", "1");
//        jsonObj.put("model", "1");
//        jsonObj.put("protocol", "tcp");
//        String js = JSONObject.toJSONString(jsonObj);
//
//        System.out.println(js+"长度为："+js.length());
//        byte[] by = tea.encrypt(js.getBytes(),keys);//加密
//        String mes=ByteBufUtil.hexDump(by);
//        System.out.println("加密的16进制为："+mes+"长度为："+mes.length());
//        System.out.println("加密成的byte："+by);
//        byte[] b = tea.decrypt(by,keys);//解密
//        System.out.println("解密stri："+new String(b));
//        System.out.println("-------------------------------------------");


        byte[] byteMsg1 = new byte[in.readableBytes()];
        in.readBytes(byteMsg1);
//        System.out.println(byteMsg1);
//        System.out.println(byteMsg1.getClass());
        String message = ByteBufUtil.hexDump(byteMsg1);
//        System.out.println("解码器接受的数据"+message+"长度为："+message.length());

        //解密
        byte[] bytes= tea.decrypt(byteMsg1, keys);
        String st= new String(bytes);

       return st;

    }
}
