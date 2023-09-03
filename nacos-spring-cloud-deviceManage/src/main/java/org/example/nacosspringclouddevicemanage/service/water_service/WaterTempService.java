package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringcloudcommonentity.water.WaterTemp;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterTempDao;
import org.springframework.beans.factory.annotation.Autowired;

public interface WaterTempService {
    int add(WaterTemp waterTemp);
    String findLately(String deviceID);
}
