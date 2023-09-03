package org.example.nacosspringclouddevicemanage.controller.water_controller;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.water.*;
import org.example.nacosspringcloudcommonentity.water.json.*;
import org.example.nacosspringclouddevicemanage.service.water_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/writeWaterData")
public class WaterWriteController {
    @Autowired
    private WaterTempService waterTempService;

    @Autowired
    private WaterQualityService waterQualityService;

    @Autowired
    private WaterCommandService waterCommandService;

    @Autowired
    private DeviceStatusService deviceStatusService;

    @Autowired
    private WaterFilterService waterFilterService;


    //接收协议解析服务的上传数据
    @PostMapping("/upLinkData")
    public void getUpData(@RequestBody JSONObject jsonObject) throws JsonProcessingException {
//        String upload_code = jsonObject.getString("upload_code");
        String upload_code = jsonObject.getString("code");
        Date date = new Date();
        //匹配上传数据码
        switch (upload_code) {
            //当前温度数据，存储
            case "220":
                WaterTemp waterTemp = new WaterTemp();
                WaterCurTempForJson waterCurTempForJson = jsonObject.getObject("data", WaterCurTempForJson.class);
                Integer curTemp = waterCurTempForJson.getTemperature();
                waterTemp.setDeviceId(jsonObject.getString("deviceID"));
                waterTemp.setCurTemp(curTemp);
                waterTemp.setTime(date);
                waterTempService.add(waterTemp);
                break;
            //当前水质
            case "201":
                WaterQuality waterQuality = new WaterQuality();
                WaterQualityForJson waterQualityForJson = jsonObject.getObject("data", WaterQualityForJson.class);
                Float raw_water = waterQualityForJson.getOld_tds();
                Float clean_water = waterQualityForJson.getNew_tds();
                waterQuality.setDeviceId(jsonObject.getString("deviceID"));
                waterQuality.setRaw_water(raw_water);
                waterQuality.setClean_water(clean_water);
                waterQuality.setTime(date);
                waterQualityService.add(waterQuality);
                break;

            //用户加热、停止加热
            case "203":
                WaterDeviceStatus heatStatus0 = new WaterDeviceStatus();
                heatStatus0.setHeat(1);
                heatStatus0.setDeviceId(jsonObject.getString("deviceID"));
                heatStatus0.setTime(date);
                deviceStatusService.updateHeat(heatStatus0);
                break;
            case "204":
                WaterDeviceStatus heatStatus1 = new WaterDeviceStatus();
                heatStatus1.setHeat(0);
                heatStatus1.setDeviceId(jsonObject.getString("deviceID"));
                heatStatus1.setTime(date);
                deviceStatusService.updateHeat(heatStatus1);
                break;

            //用户开关机
            case "207":
                WaterDeviceStatus hibStatus0 = new WaterDeviceStatus();
                hibStatus0.setDeviceId(jsonObject.getString("deviceID"));
                hibStatus0.setStandby(1);
                hibStatus0.setTime(date);
                deviceStatusService.updateStandBy(hibStatus0);
                break;
            case "208":
                WaterDeviceStatus hibStatus1 = new WaterDeviceStatus();
                hibStatus1.setDeviceId(jsonObject.getString("deviceID"));
                hibStatus1.setStandby(0);
                hibStatus1.setTime(date);
                deviceStatusService.updateStandBy(hibStatus1);
                break;

            //用户开关童锁
            case "210":
                WaterDeviceStatus childLock0 = new WaterDeviceStatus();
                childLock0.setDeviceId(jsonObject.getString("deviceID"));
                childLock0.setChildLock(1);
                childLock0.setTime(date);
                deviceStatusService.updateChildLock(childLock0);
                break;

            case "211":
                WaterDeviceStatus childLock1 = new WaterDeviceStatus();
                childLock1.setDeviceId(jsonObject.getString("deviceID"));
                childLock1.setChildLock(0);
                childLock1.setTime(date);
                deviceStatusService.updateChildLock(childLock1);
                break;

            //制水状态开关
            case "212":
                WaterDeviceStatus waterMaking0 = new WaterDeviceStatus();
                waterMaking0.setDeviceId(jsonObject.getString("deviceID"));
                waterMaking0.setWaterMaking(1);
                waterMaking0.setTime(date);
                deviceStatusService.updateWaterMaking(waterMaking0);
                break;
            case "213":
                WaterDeviceStatus waterMaking1 = new WaterDeviceStatus();
                waterMaking1.setDeviceId(jsonObject.getString("deviceID"));
                waterMaking1.setWaterMaking(0);
                waterMaking1.setTime(date);
                deviceStatusService.updateWaterMaking(waterMaking1);
                break;

            //缺水状态
            case "214":
                WaterDeviceStatus waterLacking0 = new WaterDeviceStatus();
                waterLacking0.setDeviceId(jsonObject.getString("deviceID"));
                waterLacking0.setLacking(1);
                waterLacking0.setTime(date);
                deviceStatusService.updateLacking(waterLacking0);
                break;
            case "215":
                WaterDeviceStatus waterLacking1 = new WaterDeviceStatus();
                waterLacking1.setDeviceId(jsonObject.getString("deviceID"));
                waterLacking1.setLacking(0);
                waterLacking1.setTime(date);
                deviceStatusService.updateLacking(waterLacking1);
                break;

            //漏水状态
            case "216":
                WaterDeviceStatus waterLeaking0 = new WaterDeviceStatus();
                waterLeaking0.setDeviceId(jsonObject.getString("deviceID"));
                waterLeaking0.setLeaking(1);
                waterLeaking0.setTime(date);
                deviceStatusService.updateLeaking(waterLeaking0);
                break;
            case "217":
                WaterDeviceStatus waterLeaking1 = new WaterDeviceStatus();
                waterLeaking1.setDeviceId(jsonObject.getString("deviceID"));
                waterLeaking1.setLeaking(0);
                waterLeaking1.setTime(date);
                deviceStatusService.updateLeaking(waterLeaking1);
                break;

            //在线、离线状态
            case "218":
                WaterDeviceStatus online0 = new WaterDeviceStatus();
                online0.setDeviceId(jsonObject.getString("deviceID"));
                online0.setOnline(1);
                online0.setTime(date);
                deviceStatusService.updateOnline(online0);
                break;
            case "219":
                WaterDeviceStatus online1 = new WaterDeviceStatus();
                online1.setDeviceId(jsonObject.getString("deviceID"));
                online1.setOnline(0);
                online1.setTime(date);
                deviceStatusService.updateOnline(online1);
                break;

            //信号强度
            case "109":
                WaterDeviceStatus csq = new WaterDeviceStatus();
                csq.setDeviceId(jsonObject.getString("deviceID"));
                WaterCsqForJson waterCsqForJson = jsonObject.getObject("data", WaterCsqForJson.class);
                Integer csq1 = waterCsqForJson.getCsq();
                csq.setCsq(csq1);
                csq.setTime(date);
                deviceStatusService.updateCsq(csq);
                break;

            // 匹配上传数据码
            case "200":
                // 转成 JSON 字符串
                String jsonObjectString = JSONObject.toJSONString(jsonObject);
                // 创建 ObjectMapper 实例
                ObjectMapper objectMapper = new ObjectMapper();
                // 将 JSON 字符串解析为 JsonNode 对象
                JsonNode jsonNode = objectMapper.readTree(jsonObjectString);
                // 获取 "data" 字段对应的 JsonNode
                JsonNode jsonNode1 = jsonNode.get("data");

                // 使用fields()方法获得一个迭代器，该迭代器可以用于遍历data对象的键值对
                Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode1.fields();
                while (iterator.hasNext()) {
                    // 每个键值对都是一个Map.Entry对象，其中getKey()方法返回 键，getValue()方法返回 值(在这里即即JsonNode对象)
                    Map.Entry<String, JsonNode> entry = iterator.next();
                    String key = entry.getKey();
                    JsonNode dataNode = entry.getValue();
                    // 从该JsonNode对象中获取数组的第一个元素
                    JsonNode element = dataNode.get(0);
                    // 再将其转换为具体的Java对象
                    WaterDeviceStatusForJson data = objectMapper.treeToValue(element, WaterDeviceStatusForJson.class);

                    // 温度
                    WaterTemp waterTemp1 = new WaterTemp();
                    waterTemp1.setDeviceId(data.getDeviceId());
                    waterTemp1.setCurTemp(data.getTemperature());
                    waterTemp1.setTime(date);
                    waterTempService.add(waterTemp1);

                    // 水质
                    WaterQuality waterQuality1 = new WaterQuality();
                    waterQuality1.setDeviceId(data.getDeviceId());
                    waterQuality1.setRaw_water(data.getOld_tds());
                    waterQuality1.setClean_water(data.getNew_tds());
                    waterQuality1.setTime(date);
                    waterQualityService.add(waterQuality1);

                    // 状态值、加热、最高温度
                    WaterDeviceStatus status = new WaterDeviceStatus();
                    status.setDeviceId(data.getDeviceId());
                    status.setStandby(data.getStandby());
                    status.setWaterMaking(data.getWaterMaking());
                    status.setLacking(data.getLacking());
                    status.setLeaking(data.getLeaking());
                    status.setChildLock(data.getChildLock());
                    status.setHotWater(data.getHotWater());
                    status.setColdWater(data.getColdWater());
                    status.setWashing(data.getWashing());
                    status.setChecking(data.getChecking());
                    status.setHeat(data.getHeat());
                    status.setMaxTemp(data.getMaxTemp());
                    status.setTime(date);

                    if (deviceStatusService.find(data.getDeviceId()) == null) {
                        deviceStatusService.add(status);
                    } else {
                        deviceStatusService.update(status);
                    }

                    // 滤芯
                    WaterFilter waterFilter = new WaterFilter();
                    waterFilter.setDeviceId(data.getDeviceId());
                    waterFilter.setRemainder(data.getRemainder());
                    waterFilter.setPP(data.getPP());
                    waterFilter.setGAC(data.getGAC());
                    waterFilter.setRO(data.getRO());
                    waterFilter.setT33(data.getT33());
                    waterFilter.setTime(date);

                    if (waterFilterService.find(data.getDeviceId()) == null) {
                        waterFilterService.add(waterFilter);
                    } else {
                        waterFilterService.update(waterFilter);
                    }
                }
                break;
        }
    }


    //接收应答数据，修改设备状态信息
    @PostMapping("/upAnswerData")
    public void getAnswerData(@RequestBody JSONObject jsonObject) {
        String str = jsonObject.getString("cmd_answer");
        WaterDeviceStatus status = new WaterDeviceStatus();
        String deviceID = jsonObject.getString("deviceID");
        Date deviceTime = new Date();
        status.setDeviceId(deviceID);

        /**
         * 匹配应答数据
         * 抽取各开关状态数据
         * 开机 102 关机 103 加热 112 停止加热 113 设置最高温度 114 童锁 115 解锁 116
         */
        switch (str) {
            case "102":
                status.setStandby(1);
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateStandBy(status);

                }
                break;

            case "103":
                status.setStandby(0);
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateStandBy(status);
                }
                break;

            case "112":
                status.setHeat(1);
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateHeat(status);
                }
                break;

            case "113":
                status.setHeat(0);
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateHeat(status);
                }
                break;

            case "114":
                String data = jsonObject.getString("data");
                WaterMaxTempForJson waterMaxTempForJson = jsonObject.getObject("data", WaterMaxTempForJson.class);
                System.out.println(data);
                status.setMaxTemp(waterMaxTempForJson.getMax());
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateTemp(status);
                }
                break;

            case "115":
                status.setChildLock(1);
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateChildLock(status);
                }
                break;

            case "116":
                status.setChildLock(0);
                status.setTime(deviceTime);
                if (deviceStatusService.find(deviceID) == null) {
                    deviceStatusService.add(status);
                } else {
                    deviceStatusService.updateChildLock(status);
                }
                break;
        }
    }

    //存储用户下传指令
    @PostMapping("/downLinkData")
    public void getDownData(@RequestBody DownData downData) {
        waterCommandService.add(downData);
    }

    @PostMapping("/delData")
    public Map<String, Object> delById(@RequestBody DownData downData) {
        Map<String, Object> map = new HashMap<>();
        waterCommandService.delCommand(downData);
        map.put("result", "删除成功");
//        PageHelper.startPage(downData.getPageNum(), downData.getPageSize());
        List<DownData> dataList = waterCommandService.findByBar(downData);
        PageInfo<DownData> pageInfo = new PageInfo<>(dataList);
        map.put("总页数", pageInfo.getPages());
        map.put("总记录数", pageInfo.getTotal());
        map.put("当前页数", pageInfo.getPageNum());
        map.put("当前页面记录数", pageInfo.getSize());
        map.put("dataList", pageInfo.getList());
        return map;
    }
}
