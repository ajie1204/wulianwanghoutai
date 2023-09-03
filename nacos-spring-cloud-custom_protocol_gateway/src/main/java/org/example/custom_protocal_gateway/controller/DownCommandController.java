package org.example.custom_protocal_gateway.controller;

import org.example.nacosspringcloudcommonentity.DownData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/gateway")
public class DownCommandController {

    @Resource
    WaterClient waterClient;

    @PostMapping("/down")
    public void down(@RequestBody DownData downData) throws InterruptedException {
        /*根据所属系统字段将指令传给对应的系统接口*/
        System.out.println("success2");
        waterClient.downForward(downData);
    }




    @FeignClient(name = "waterPurifier",contextId = "down")
    public interface WaterClient{
        @PostMapping("/water/down")
        void downForward(@RequestBody DownData downData);
    }


}
