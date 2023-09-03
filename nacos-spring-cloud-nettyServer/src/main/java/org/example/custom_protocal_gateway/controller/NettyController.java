package org.example.custom_protocal_gateway.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.example.custom_protocal_gateway.entity.CommandFrame;
import org.example.custom_protocal_gateway.service.NettyService;
import org.example.custom_protocal_gateway.service.feign.DeviceManageService;
import org.example.nacosspringcloudcommonentity.DownData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/netty")
public class NettyController {

    @Autowired
    NettyService nettyService;

    @Autowired
    DeviceManageService deviceManageService;

    @PostMapping("/down")
    public void downHeating(@RequestBody DownData downData)  {
        String deviceId = downData.getDeviceId();
        CommandFrame commandFrame = new CommandFrame();
        commandFrame.setData(downData.getData());
        commandFrame.setDeviceId(downData.getDeviceId());
        commandFrame.setSerialNumber(downData.getSerialNumber());
        commandFrame.setTime(downData.getTime());
        commandFrame.setCmd(downData.getCmd());



        String jsonCommand = JSON.toJSONString(commandFrame,filter);
        System.out.println("success4");
        /*判断设备的协议类型调用对用的方法传指令*/
//        String protocol = downData.getProtocol();
        String protocol = "mqtt";
        switch (protocol){
            case "tcp" :
                nettyService.sendTCPCommand(deviceId,jsonCommand);
                break;
            case "mqtt" :
                nettyService.sendMQTTCommand(deviceId,jsonCommand);
                break;
        }

    }

    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if(v==null) {
                return "";
            }
            return v;
        }
    };





}
