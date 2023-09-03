package org.example.nacosspringcloudinteract.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringcloudcommonentity.UplinkCommand;
import org.example.nacosspringcloudcommonentity.water.WaterDeviceStatus;
import org.example.nacosspringcloudcommonentity.water.WaterFilter;
import org.example.nacosspringcloudcommonentity.water.WaterQuality;
import org.example.nacosspringcloudcommonentity.water.WaterTemp;
import org.example.nacosspringcloudcommonentity.water.json.*;
import org.example.nacosspringcloudinteract.service.DeviceService;
import org.example.nacosspringcloudinteract.service.UpDataService;
import org.example.nacosspringcloudinteract.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpDataServiceImpl implements UpDataService {


    @Autowired
    WebSocket webSocket;

    @Autowired
    DeviceService deviceService;


    @SneakyThrows
    @Override
    public void pushData(JSONObject jsonObject) {
        String upload_code = jsonObject.getString("upload_code");
        String deviceId = jsonObject.getString("deviceID");
        Device device = deviceService.getDevice(deviceId);
        Integer userId = device.getUserId();
        Date date = new Date();
        //匹配上传数据码
        switch (upload_code) {
            //当前温度数据，存储
            case "200":
                WaterTemp waterTemp = new WaterTemp();
                WaterCurTempForJson waterCurTempForJson = jsonObject.getObject("data", WaterCurTempForJson.class);
                Integer curTemp = waterCurTempForJson.getTemperature();
                waterTemp.setDeviceId(jsonObject.getString("deviceID"));
                waterTemp.setCurTemp(curTemp);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("waterTemp",waterTemp);
                webSocket.sendInfo(map1,userId);

                break;
            //当前水质
            case "201":
                WaterQuality waterQuality = new WaterQuality();
                waterQuality.setDeviceId(jsonObject.getString("deviceID"));
                WaterQualityForJson waterQualityForJson = jsonObject.getObject("data", WaterQualityForJson.class);
                Float raw_water = waterQualityForJson.getOld_tds();
                Float clean_water = waterQualityForJson.getNew_tds();
                waterQuality.setRaw_water(raw_water);
                waterQuality.setClean_water(clean_water);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("waterQuality",waterQuality);
                webSocket.sendInfo(map2,userId);
                break;

            //用户加热、停止加热
            case "203":
                WaterDeviceStatus heatStatus0 = new WaterDeviceStatus();
                heatStatus0.setHeat(1);
                heatStatus0.setDeviceId(jsonObject.getString("deviceID"));
                Map<String, Object> map3 = new HashMap<>();
                map3.put("heat",heatStatus0);
                webSocket.sendInfo(map3,userId);
                break;
            case "204":
                WaterDeviceStatus heatStatus1 = new WaterDeviceStatus();
                heatStatus1.setHeat(0);
                heatStatus1.setDeviceId(jsonObject.getString("deviceID"));
                Map<String, Object> map4 = new HashMap<>();
                map4.put("heat",heatStatus1);
                webSocket.sendInfo(map4,userId);
                break;

            //用户开关机
            case "207":
                WaterDeviceStatus hibStatus0 = new WaterDeviceStatus();
                hibStatus0.setDeviceId(jsonObject.getString("deviceID"));
                hibStatus0.setStandby(1);
                Map<String, Object> map5 = new HashMap<>();
                map5.put("open",hibStatus0);
                webSocket.sendInfo(map5,userId);
                break;
            case "208":
                WaterDeviceStatus hibStatus1 = new WaterDeviceStatus();
                hibStatus1.setDeviceId(jsonObject.getString("deviceID"));
                hibStatus1.setStandby(0);
                hibStatus1.setTime(date);
                Map<String, Object> map6 = new HashMap<>();
                map6.put("open",hibStatus1);
                webSocket.sendInfo(map6,userId);
                break;

            //用户开关童锁
            case "210":
                WaterDeviceStatus childLock0 = new WaterDeviceStatus();
                childLock0.setDeviceId(jsonObject.getString("deviceID"));
                childLock0.setChildLock(1);
                Map<String, Object> map7 = new HashMap<>();
                map7.put("childLock",childLock0);
                webSocket.sendInfo(map7,userId);
                break;
            case "211":
                WaterDeviceStatus childLock1 = new WaterDeviceStatus();
                childLock1.setDeviceId(jsonObject.getString("deviceID"));
                childLock1.setChildLock(0);
                Map<String, Object> map8 = new HashMap<>();
                map8.put("childLock",childLock1);
                webSocket.sendInfo(map8,userId);

                break;

            //制水状态开关
            case "212":
                WaterDeviceStatus waterMaking0 = new WaterDeviceStatus();
                waterMaking0.setDeviceId(jsonObject.getString("deviceID"));
                waterMaking0.setWaterMaking(1);
                Map<String, Object> map9 = new HashMap<>();
                map9.put("waterMaking",waterMaking0);
                webSocket.sendInfo(map9,userId);
                break;
            case "213":
                WaterDeviceStatus waterMaking1 = new WaterDeviceStatus();
                waterMaking1.setDeviceId(jsonObject.getString("deviceID"));
                waterMaking1.setWaterMaking(0);
                Map<String, Object> map10 = new HashMap<>();
                map10.put("waterMaking",waterMaking1);
                webSocket.sendInfo(map10,userId);
                break;

            //缺水状态
            case "214":
                WaterDeviceStatus waterLacking0 = new WaterDeviceStatus();
                waterLacking0.setDeviceId(jsonObject.getString("deviceID"));
                waterLacking0.setLacking(1);
                Map<String, Object> map11 = new HashMap<>();
                map11.put("waterLacking",waterLacking0);
                webSocket.sendInfo(map11,userId);
                break;

            case "215":
                WaterDeviceStatus waterLacking1 = new WaterDeviceStatus();
                waterLacking1.setDeviceId(jsonObject.getString("deviceID"));
                waterLacking1.setLacking(0);
                Map<String, Object> map12 = new HashMap<>();
                map12.put("waterLacking",waterLacking1);
                webSocket.sendInfo(map12,userId);
                break;

            //漏水状态
            case "216":
                WaterDeviceStatus waterLeaking0 = new WaterDeviceStatus();
                waterLeaking0.setDeviceId(jsonObject.getString("deviceID"));
                waterLeaking0.setLeaking(1);
                Map<String, Object> map13 = new HashMap<>();
                map13.put("waterLeaking",waterLeaking0);
                webSocket.sendInfo(map13,userId);
                break;

            case "217":
                WaterDeviceStatus waterLeaking1 = new WaterDeviceStatus();
                waterLeaking1.setDeviceId(jsonObject.getString("deviceID"));
                waterLeaking1.setLeaking(0);
                Map<String, Object> map14 = new HashMap<>();
                map14.put("waterLeaking",waterLeaking1);
                webSocket.sendInfo(map14,userId);
                break;

            //在线、离线状态
            case "218":
                WaterDeviceStatus online0 = new WaterDeviceStatus();
                online0.setDeviceId(jsonObject.getString("deviceID"));
                online0.setOnline(1);
                Map<String, Object> map15 = new HashMap<>();
                map15.put("online",online0);
                webSocket.sendInfo(map15,userId);
                break;
            case "219":
                WaterDeviceStatus online1 = new WaterDeviceStatus();
                online1.setDeviceId(jsonObject.getString("deviceID"));
                online1.setOnline(0);
                Map<String, Object> map16 = new HashMap<>();
                map16.put("online",online1);
                webSocket.sendInfo(map16,userId);
                break;

            //信号强度
            case "109":
                WaterDeviceStatus csq = new WaterDeviceStatus();
                csq.setDeviceId(jsonObject.getString("deviceID"));
                WaterCsqForJson waterCsqForJson = jsonObject.getObject("data", WaterCsqForJson.class);
                Integer csq1 = waterCsqForJson.getCsq();
                csq.setCsq(csq1);
                Map<String, Object> map17 = new HashMap<>();
                map17.put("csq",csq);
                webSocket.sendInfo(map17,userId);
                break;

            //所有状态
            case "220":
                WaterDeviceStatusForJson data = jsonObject.getObject("data",WaterDeviceStatusForJson.class);
                //温度
                WaterTemp waterTemp1 = new WaterTemp();
                waterTemp1.setDeviceId(jsonObject.getString("deviceID"));
                waterTemp1.setCurTemp(data.getTemperature());
                Map<String, Object> map18 = new HashMap<>();
                map18.put("waterTemp",waterTemp1);
                webSocket.sendInfo(map18,userId);
                //水质
                WaterQuality waterQuality1 = new WaterQuality();
                waterQuality1.setDeviceId(jsonObject.getString("deviceID"));
                waterQuality1.setRaw_water(data.getOld_tds());
                waterQuality1.setClean_water(data.getNew_tds());
                Map<String, Object> map19 = new HashMap<>();
                map19.put("waterQuality",waterQuality1);
                webSocket.sendInfo(map19,userId);
//                //状态值、加热、最高温度
                WaterDeviceStatus status = new WaterDeviceStatus();
                status.setDeviceId(jsonObject.getString("deviceID"));
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
                Map<String, Object> map20 = new HashMap<>();
                map20.put("status",status);
                webSocket.sendInfo(map20,userId);
//                status.setTime(date);
//                if (deviceStatusService.find(jsonObject.getString("deviceID"))==null){
//                    deviceStatusService.add(status);
//                } else {
//                    deviceStatusService.update(status);
//
//                }
//                //滤芯
                WaterFilter waterFilter = new WaterFilter();
                waterFilter.setDeviceId(jsonObject.getString("deviceID"));
                waterFilter.setPP(data.getPP());
                waterFilter.setGAC(data.getGAC());
                waterFilter.setRO(data.getRO());
                waterFilter.setT33(data.getT33());
                Map<String, Object> map21 = new HashMap<>();
                map21.put("filter",waterFilter);
                webSocket.sendInfo(map21,userId);
//                waterFilter.setTime(date);
//                if (waterFilterService.find(jsonObject.getString("deviceID"))==null){
//                    waterFilterService.add(waterFilter);
//                } else {
//                    waterFilterService.update(waterFilter);
//
//                }
//                break;
    }
}

    @SneakyThrows
    @Override
    public void pushAnswer(JSONObject jsonObject) {
        String str = jsonObject.getString("cmd_answer");

        String deviceID = jsonObject.getString("deviceID");
        Date deviceTime = new Date();
        Device device = deviceService.getDevice(deviceID);
        Integer userId = device.getUserId();
        Date date = new Date();

        /**
         * 匹配应答数据
         * 抽取各开关状态数据
         * 开机 102 关机 103 加热 112 停止加热 113 设置最高温度 114 童锁 115 解锁 116
         */
        switch (str){
            case "102":
                WaterDeviceStatus hibStatus0 = new WaterDeviceStatus();
                hibStatus0.setDeviceId(jsonObject.getString("deviceID"));
                hibStatus0.setStandby(1);
                hibStatus0.setTime(date);
                Map<String, Object> map2 = new HashMap<>();
                map2.put("open",hibStatus0);
                webSocket.sendInfo(map2,userId);
                break;

            case "103":
                WaterDeviceStatus hibStatus1 = new WaterDeviceStatus();
                hibStatus1.setDeviceId(jsonObject.getString("deviceID"));
                hibStatus1.setStandby(0);
                hibStatus1.setTime(date);
                Map<String, Object> map6 = new HashMap<>();
                map6.put("open",hibStatus1);
                webSocket.sendInfo(map6,userId);
                break;

            case "112":
                WaterDeviceStatus heatStatus0 = new WaterDeviceStatus();
                heatStatus0.setHeat(1);
                heatStatus0.setDeviceId(jsonObject.getString("deviceID"));
                Map<String, Object> map3 = new HashMap<>();
                map3.put("heat",heatStatus0);
                webSocket.sendInfo(map3,userId);
                break;

            case "113":
                WaterDeviceStatus heatStatus1 = new WaterDeviceStatus();
                heatStatus1.setHeat(0);
                heatStatus1.setDeviceId(jsonObject.getString("deviceID"));
                Map<String, Object> map4 = new HashMap<>();
                map4.put("heat",heatStatus1);
                webSocket.sendInfo(map4,userId);
                break;

            case "114":
                String data = jsonObject.getString("data");
                WaterMaxTempForJson waterMaxTempForJson =jsonObject.getObject("data", WaterMaxTempForJson.class);
                float maxTemp = waterMaxTempForJson.getMax();
                Map<String, Object> map5 = new HashMap<>();
                map5.put("maxTemp",maxTemp);
                webSocket.sendInfo(map5,userId);
                break;

            case "115":
                WaterDeviceStatus childLock0 = new WaterDeviceStatus();
                childLock0.setDeviceId(jsonObject.getString("deviceID"));
                childLock0.setChildLock(1);
                Map<String, Object> map7 = new HashMap<>();
                map7.put("childLock",childLock0);
                webSocket.sendInfo(map7,userId);
                break;

            case "116":
                WaterDeviceStatus childLock1 = new WaterDeviceStatus();
                childLock1.setDeviceId(jsonObject.getString("deviceID"));
                childLock1.setChildLock(0);
                Map<String, Object> map8 = new HashMap<>();
                map8.put("childLock",childLock1);
                webSocket.sendInfo(map8,userId);
        }
    }
}
