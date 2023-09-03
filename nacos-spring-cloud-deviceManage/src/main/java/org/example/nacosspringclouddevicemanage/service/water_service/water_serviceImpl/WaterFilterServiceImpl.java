package org.example.nacosspringclouddevicemanage.service.water_service.water_serviceImpl;

import org.example.nacosspringcloudcommonentity.water.WaterFilter;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterFilterDao;
import org.example.nacosspringclouddevicemanage.service.water_service.WaterFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WaterFilterServiceImpl implements WaterFilterService {

    @Autowired
    private WaterFilterDao waterFilterDao;

    @Override
    public int add(WaterFilter waterFilter) {
        return waterFilterDao.add(waterFilter);
    }

    @Override
    public WaterFilter find(String deviceId) {
        return waterFilterDao.find(deviceId);
    }

    @Override
    public WaterFilter findRemainder() {
        return waterFilterDao.findRemainder();
    }

    @Override
    public int update(WaterFilter waterFilter) {
        return waterFilterDao.update(waterFilter);
    }

    @Override
    public int updateAuto(String deviceId) {
        return waterFilterDao.updateAuto(deviceId);
    }

    @Override
    public Date findTime(String deviceId) {
        return waterFilterDao.findTime(deviceId);
    }
}
