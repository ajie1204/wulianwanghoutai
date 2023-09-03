package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringcloudcommonentity.water.WaterDeviceStatus;

public interface DeviceStatusService {
    int add(WaterDeviceStatus waterDeviceStatus);
    int update(WaterDeviceStatus waterDeviceStatus);
    int updateStandBy(WaterDeviceStatus waterDeviceStatus);
    int updateHeat(WaterDeviceStatus waterDeviceStatus);
    int updateTemp(WaterDeviceStatus waterDeviceStatus);
    int updateChildLock(WaterDeviceStatus waterDeviceStatus);
    int updateWaterMaking(WaterDeviceStatus waterDeviceStatus);
    int updateLacking(WaterDeviceStatus waterDeviceStatus);
    int updateLeaking(WaterDeviceStatus waterDeviceStatus);
    int updateOnline(WaterDeviceStatus waterDeviceStatus);
    //更新信号强度
    int updateCsq(WaterDeviceStatus waterDeviceStatus);
    WaterDeviceStatus findLately(String deviceId);
    WaterDeviceStatus find(String deviceId);
}
