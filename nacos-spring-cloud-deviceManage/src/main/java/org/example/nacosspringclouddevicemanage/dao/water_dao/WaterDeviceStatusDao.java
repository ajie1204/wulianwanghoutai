package org.example.nacosspringclouddevicemanage.dao.water_dao;

import org.example.nacosspringcloudcommonentity.water.WaterDeviceStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterDeviceStatusDao {
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
    //返回设备最新的状态数据
    WaterDeviceStatus findLately(String deviceId);
    WaterDeviceStatus find(String deviceId);
}
