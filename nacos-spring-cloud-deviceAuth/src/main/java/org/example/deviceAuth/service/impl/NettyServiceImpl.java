package org.example.deviceAuth.service.impl;

import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.deviceAuth.netty.channel.NettySocketHolder;
import org.example.deviceAuth.service.NettyService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class NettyServiceImpl implements NettyService {
    @Override
    public void sendTCPCommand(String deviceId, String jsonCommand) {
       /* CommandFrame frame = JsonUtil.jsonToObject(jsonCommand, CommandFrame.class);
        String did = frame.getDeviceId();
        String data = frame.getData();*/
        NioSocketChannel nioSocketChannel = NettySocketHolder.get(deviceId);

        nioSocketChannel.writeAndFlush(jsonCommand);
        log.info("发送给设备的信息为"+jsonCommand);
    }
}
