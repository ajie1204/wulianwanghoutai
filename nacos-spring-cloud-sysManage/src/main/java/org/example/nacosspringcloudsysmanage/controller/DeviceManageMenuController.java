package org.example.nacosspringcloudsysmanage.controller;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudsysmanage.service.feignService.DeviceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/deviceManage")
public class DeviceManageMenuController {

    @Autowired
    private DeviceManageService deviceManageService;

    //获取所有设备信息
    @GetMapping("/getAllDevice/{pageNum}/{pageSize}")
    public Map<String,Object>  deviceMap(@PathVariable int pageNum,@PathVariable int pageSize){
        return deviceManageService.getDeviceMap(pageNum,pageSize);
    }

    @PostMapping("/findByBar")
    public Map<String,Object> getDeviceByBar(@RequestBody Device device){
        return deviceManageService.getDeviceByBar(device);
    }


    @PostMapping("/addDevice")
    public Map<String,Object> addDevice(@RequestBody Device device){
        return deviceManageService.addDevice(device);
    }


    @PostMapping("/updateDevice")
    public Map<String,Object> updateDevice(@RequestBody Device device){
        return deviceManageService.updateDevice(device);
    }


    @PostMapping("/delDevice")
    public Map<String,Object> delDevice(@RequestBody Device device){
        return deviceManageService.delDevice(device);
    }

}
