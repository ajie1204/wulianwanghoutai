package org.example.custom_protocal_gateway.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.CharsetUtil;
import org.example.custom_protocal_gateway.netty.handler.*;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        InetSocketAddress socketaddr = channel.localAddress();
        int port = socketaddr.getPort();
        /*根据端口号添加编解码器以及handler*/
        switch (port){
            case 8082 :
                System.out.println("端口8082");
                pipeline.addLast(new SocketReceiveDataHandler());
                pipeline.addLast(new TcpHandler());//自定义解码器
                pipeline.addLast(new TCPServerHandler());// 请求TCP处理器
                break;
            case 9001 :
                System.out.println("端口9001");
                pipeline.addLast(new HttpServerCodec());// http 编解码
                pipeline.addLast("httpAggregator",new HttpObjectAggregator(512*1024)); // http 消息聚合器                                                                     512*1024为接收的最大contentlength
                pipeline.addLast(new HttpServerHandler());// 请求HTP处理器
                break;
            case 8090:
                System.out.println("端口9000");
                pipeline.addLast("encoder", MqttEncoder.INSTANCE);
                pipeline.addLast("decoder", new MqttDecoder());
                pipeline.addLast("mqttHandler",new MqttServerHandler());// 服务端业务逻辑
                break;


        }




    }

}
