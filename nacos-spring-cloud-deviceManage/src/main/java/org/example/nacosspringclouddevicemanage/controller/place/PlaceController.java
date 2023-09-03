package org.example.nacosspringclouddevicemanage.controller.place;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringclouddevicemanage.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    DeviceService deviceService;

    @PostMapping("/unbound")
    public int unbound(@RequestBody Integer placeId){
        System.out.println("方法被调用");

        List<Device> devices = deviceService.selectByPlace(placeId);
        if (devices.isEmpty()){
            return -1;
        }
        int unbound = deviceService.unbound(placeId);
        return unbound;
        /*设备解绑*/

    }



    @PostMapping("/openPlace")
    public List<Device> openPlace(@RequestBody Integer placeId){

        List<Device> devices = deviceService.selectByPlace(placeId);
       return devices;

    }


    @PostMapping("/selectUnbound")
    public List<Device> selectUnbound(@RequestBody Integer userId){

        List<Device> devices = deviceService.selectUnbound(userId);
        return devices;

    }


    @GetMapping("/bindPlace/{deviceId}/{placeId}")
    public int bindPlace(@PathVariable("deviceId") String deviceId, @PathVariable("placeId") Integer placeId){

        int result = deviceService.bindPlace(deviceId,placeId);
        return result;

    }


    @GetMapping("/unboundOne/{deviceId}")
    public  int unboundOne(@PathVariable("deviceId") String deviceId){
        return deviceService.unboundOne(deviceId);
    }



}
