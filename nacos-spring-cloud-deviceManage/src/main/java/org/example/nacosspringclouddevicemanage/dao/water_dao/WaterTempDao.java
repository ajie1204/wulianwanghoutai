package org.example.nacosspringclouddevicemanage.dao.water_dao;

import org.example.nacosspringcloudcommonentity.water.WaterTemp;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterTempDao {
    //新增数据
    int add(WaterTemp waterTemp);

    //查询最新数据
    String findLately(String deviceID);
}
