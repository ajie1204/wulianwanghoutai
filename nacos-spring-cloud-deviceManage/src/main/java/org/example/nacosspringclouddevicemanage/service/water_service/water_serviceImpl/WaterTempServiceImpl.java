package org.example.nacosspringclouddevicemanage.service.water_service.water_serviceImpl;

import org.example.nacosspringcloudcommonentity.water.WaterTemp;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterTempDao;
import org.example.nacosspringclouddevicemanage.service.water_service.WaterTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterTempServiceImpl implements WaterTempService {
    @Autowired
    private WaterTempDao waterTempDao;

    @Override
    public int add(WaterTemp waterTemp) {
        return waterTempDao.add(waterTemp);
    }

    @Override
    public String findLately(String deviceID) {
        return waterTempDao.findLately(deviceID);
    }

}
