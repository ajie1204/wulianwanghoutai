package org.example.custom_protocal_gateway.service.feign;


import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "deviceManage")
public interface DeviceManageService {

    @GetMapping("device/getDevice/{deviceId}")
    Device getDeviceById(@PathVariable String deviceId);



}
