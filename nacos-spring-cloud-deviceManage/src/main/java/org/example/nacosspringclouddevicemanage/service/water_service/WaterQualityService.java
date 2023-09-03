package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringcloudcommonentity.water.WaterQuality;

public interface WaterQualityService {
    int add(WaterQuality waterQuality);
    WaterQuality findLately(String deviceID);
}
