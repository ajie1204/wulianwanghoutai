package org.example.nacosspringcloudinteract.websocket;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private Integer userId;
    private Logger logger  = LoggerFactory.getLogger(WebSocket.class);
    private Session session;
    //储存所有WebSocket连接
    private static CopyOnWriteArraySet<WebSocket> clients = new CopyOnWriteArraySet<>();

    private static ConcurrentHashMap<Integer,WebSocket> webSocketMap = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(@PathParam("userId") Integer userId,Session session) {
        this.session = session;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            //在线数加1
            addOnlineCount();
        }
        log.info("用户连接:"+userId+",当前在线人数为:" + onlineCount);

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:"+userId+",网络异常!!!!!!");
        }

        Map<String, Object> map = new HashMap<>();
        /*map.put("pack", packetService.getInitPackets());*/
        ObjectMapper mapper = new ObjectMapper();
        try {
            String msg = mapper.writeValueAsString(map);
            session.getBasicRemote().sendText(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients.add(this);

        logger.info("新连接"+session.getId());
    }


    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }


    @OnClose
    public void onClose() {
        clients.remove(this);
        logger.info("连接断开！");
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("有异常啦");
        //error.printStackTrace();
    }



    /**
     * 推送消息给所有WebSocket客户端
     * @param msg
     */
    public void sendMsg(String msg) {
        for (WebSocket webSocket : clients) {
            try {
                webSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void pushDataToUsers(Map<String,Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String msg = mapper.writeValueAsString(map);
            this.sendMessage(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 推送给固定用户
     * @param map
     * @param userId
     * @throws IOException
     */
    public  void sendInfo(Map<String,Object> map,Integer userId) throws IOException {
        log.info("发送消息到:"+userId+"，报文:"+map);
        if(webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).pushDataToUsers(map);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }

}
