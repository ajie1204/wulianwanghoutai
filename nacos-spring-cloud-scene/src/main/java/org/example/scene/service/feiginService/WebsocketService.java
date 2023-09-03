package org.example.scene.service.feiginService;


import org.example.nacosspringcloudcommonentity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "interact")
public interface WebsocketService {



    @PostMapping("/websocket/message/push")
    public void pushMessage(@RequestParam("userId")Integer userId,@RequestBody Message message);



}
