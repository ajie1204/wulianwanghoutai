package org.example.deviceAuth.service.feign;


import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@FeignClient(name = "deviceManage")
public interface DeviceManageService {

    @GetMapping("device/getDevice/{deviceId}")
    Device getDeviceById(@PathVariable String deviceId);
    @PostMapping("device/addDevice")
    Map<String,Object> addDevice(@RequestBody Device device);
    @GetMapping("device/checkDevice")
    String checkDevice();
    @PostMapping("device/updataCheckDevice")
    void updataCheckDevice(@RequestBody Device device);





}
