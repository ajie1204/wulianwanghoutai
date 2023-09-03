package org.example.nacosspringclouddevicemanage.dao.water_dao;

import org.example.nacosspringcloudcommonentity.water.WaterQuality;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterQualityDao {
    int add(WaterQuality waterQuality);
    //查询最新的水质数据
    WaterQuality findLately(String deviceID);
}
