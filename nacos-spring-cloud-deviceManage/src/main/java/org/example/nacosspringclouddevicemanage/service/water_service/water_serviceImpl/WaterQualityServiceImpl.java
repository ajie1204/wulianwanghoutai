package org.example.nacosspringclouddevicemanage.service.water_service.water_serviceImpl;


import org.example.nacosspringcloudcommonentity.water.WaterQuality;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterQualityDao;
import org.example.nacosspringclouddevicemanage.service.water_service.WaterQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterQualityServiceImpl implements WaterQualityService {

    @Autowired
    private WaterQualityDao waterQualityDao;

    @Override
    public int add(WaterQuality waterQuality) {
        return waterQualityDao.add(waterQuality);
    }

    @Override
    public WaterQuality findLately(String deviceID) {
        return waterQualityDao.findLately(deviceID);
    }
}
