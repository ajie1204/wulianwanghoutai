package org.example.nacosspringcloudinteract.service;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Service
@FeignClient("deviceManage")
public interface DeviceService {


    //查找设备所属系统
    @GetMapping("/device/systemName/{deviceId}")
    String systemName(@PathVariable String deviceId);

    //查找该用户下的所有设备
    @GetMapping("/device/getAllDevice/{userId}")
    List<Device> findAll(@PathVariable int userId);

    @PostMapping("/place/unbound")
    int unbound(@RequestBody Integer placeId);


    @PostMapping("/place/openPlace")
    List<Device> openPlace(@RequestBody Integer placeId);


    @PostMapping("/place/selectUnbound")
    List<Device> selectUnbound(@RequestBody Integer userId);

    @GetMapping("/place/bindPlace/{deviceId}/{placeId}")
    int bindPlace(@PathVariable("deviceId") String deviceId,@PathVariable("placeId") Integer placeId);

    @GetMapping("/place/unboundOne/{deviceId}")
    int unboundOne(@PathVariable("deviceId") String deviceId);

    @GetMapping("/device/findForIndex/{userId}")
    List<Device> getIndexMap(@PathVariable Integer userId);


    //根据设备id查找设备
    @GetMapping("/device/getDevice/{deviceId}")
    Device getDevice(@PathVariable String deviceId);

    //激活设备
    @PostMapping("/device/activate")
     int activateDevice(@RequestParam String deviceId, @RequestParam Integer userId, @RequestParam String deviceName);

    /**
     * 查找场所
     * @param userId
     * @return
     */
    @PostMapping("/device/activate")
    List selectPlace(Integer userId);
    
}
