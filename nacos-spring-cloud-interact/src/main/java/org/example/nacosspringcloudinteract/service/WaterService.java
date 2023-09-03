package org.example.nacosspringcloudinteract.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("deviceManage")
public interface WaterService {


    /*返回饮水界面的所有数据 状态数据 水温 水质 剩余使用天数与滤芯寿命*/
    @GetMapping("/readWaterData/{deviceId}")
    Map<String,Object> getDeviceData(@PathVariable String deviceId);


    @PostMapping("/writeWaterData/upLinkData")
    void getUpData(@RequestBody JSONObject jsonObject);

    //接收应答数据，修改设备状态信息
    @PostMapping("/writeWaterData/upAnswerData")
    public void getAnswerData(@RequestBody JSONObject jsonObject);
}
