package org.example.nacosspringcloudinteract.controller.websocket;

import org.example.nacosspringcloudcommonentity.Message;
import org.example.nacosspringcloudcommonentity.Task;
import org.example.nacosspringcloudinteract.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/websocket")
public class WebsocketController {

    @Autowired
    WebSocket webSocket;

    @PostMapping("/message/push")
    public void pushMessage (@RequestParam("userId")Integer userId,@RequestBody Message message){
        System.out.println("Push方法被调用");
        HashMap<String, Object> map = new HashMap<>();
        map.put("message",message);
        try {
            webSocket.sendInfo(map,userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/upAnswerData/push")
    public void pushUpAnswerData (@RequestParam("userId")Integer userId,@RequestBody String message){

        HashMap<String, Object> map = new HashMap<>();
        map.put("upAnswer",message);
        try {
            webSocket.sendInfo(map,userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
