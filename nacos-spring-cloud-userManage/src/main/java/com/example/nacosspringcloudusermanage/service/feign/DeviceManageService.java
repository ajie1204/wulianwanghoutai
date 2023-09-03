package com.example.nacosspringcloudusermanage.service.feign;


import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(name = "deviceManage")
public interface DeviceManageService {
    @PostMapping("device/unActivate")
    int unActivate(@RequestParam String deviceId);

    @GetMapping("/device/getAllDevice/{userId}")
    List<Device> findAll(@PathVariable int userId);
}
