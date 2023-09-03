package org.example.nacosspringclouddevicemanage.service.water_service;

import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.water.WaterCommand;

import java.util.List;

public interface WaterCommandService {
    int add(DownData downData);

    List<DownData> findByPage();
    //搜索框 userId deviceId cmd time
    List<DownData> findByBar(DownData downData);
    void delCommand(DownData downData);
}
