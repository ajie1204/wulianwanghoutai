package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringcloudcommonentity.water.WaterFilter;
import org.example.nacosspringcloudcommonentity.water.WaterLife;

import java.util.Date;

public interface WaterLifeService {
    WaterLife countLife(Date time);
    WaterLife count(WaterFilter waterFilter);
}
