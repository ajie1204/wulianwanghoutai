package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringcloudcommonentity.water.WaterFilter;

import java.util.Date;

public interface WaterFilterService {
    int add(WaterFilter waterFilter);
    WaterFilter find(String deviceId);
    WaterFilter findRemainder();
    int update(WaterFilter waterFilter);
    int updateAuto(String deviceId);
    Date findTime(String deviceId);
}
