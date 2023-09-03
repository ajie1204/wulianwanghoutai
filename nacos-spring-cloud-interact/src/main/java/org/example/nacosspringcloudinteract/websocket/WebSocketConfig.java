package org.example.nacosspringcloudinteract.websocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: Rain
 * @Description:
 * @Date: Created on 2018/11/6 9:15
 */
@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter exporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 解决直接注入packetService为null的问题
     * @param packetService
     */
    /*@Autowired
    public void setPacketService(PacketService packetService) {
        WebSocket.packetService = packetService;
    }*/
}
