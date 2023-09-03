package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringclouddevicemanage.entity.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@FeignClient("place")
public interface Device1Service {

    /**
     * 根据 userId查找 场所
     * @param userId
     * @return
     */
    @PostMapping("/place/select")
    List<Device> selectPlace(Integer userId);
}
