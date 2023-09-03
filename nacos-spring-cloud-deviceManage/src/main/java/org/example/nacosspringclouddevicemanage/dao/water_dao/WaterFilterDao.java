package org.example.nacosspringclouddevicemanage.dao.water_dao;

import org.example.nacosspringcloudcommonentity.water.WaterFilter;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WaterFilterDao {
    int add(WaterFilter waterFilter);
    WaterFilter find(String deviceId);
    WaterFilter findRemainder();
    int update(WaterFilter waterFilter);
    //每天自动改变每种滤芯的使用寿命
    int updateAuto(String deviceId);
    //查找滤芯开始使用的时间
    Date findTime(String deviceId);
}
