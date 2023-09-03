package org.example.nacosspringclouddevicemanage.service.water_service.water_serviceImpl;

import org.example.nacosspringcloudcommonentity.water.WaterDeviceStatus;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterDeviceStatusDao;
import org.example.nacosspringclouddevicemanage.service.water_service.DeviceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceStatusServiceImpl implements DeviceStatusService {
    @Autowired
    private WaterDeviceStatusDao waterDeviceStatusDao;

    @Override
    public int add(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.add(waterDeviceStatus);
    }

    @Override
    public int update(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.update(waterDeviceStatus);
    }

    @Override
    public int updateStandBy(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateStandBy(waterDeviceStatus);
    }

    @Override
    public int updateHeat(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateHeat(waterDeviceStatus);
    }

    @Override
    public int updateTemp(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateTemp(waterDeviceStatus);
    }

    @Override
    public int updateChildLock(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateChildLock(waterDeviceStatus);
    }

    @Override
    public int updateWaterMaking(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateWaterMaking(waterDeviceStatus);
    }

    @Override
    public int updateLacking(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateLacking(waterDeviceStatus);
    }

    @Override
    public int updateLeaking(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateLeaking(waterDeviceStatus);
    }

    @Override
    public int updateOnline(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateOnline(waterDeviceStatus);
    }

    @Override
    public int updateCsq(WaterDeviceStatus waterDeviceStatus) {
        return waterDeviceStatusDao.updateCsq(waterDeviceStatus);
    }

    @Override
    public WaterDeviceStatus findLately(String deviceId) {
        return waterDeviceStatusDao.findLately(deviceId);
    }

    @Override
    public WaterDeviceStatus find(String deviceId) {
        return waterDeviceStatusDao.find(deviceId);
    }


}
