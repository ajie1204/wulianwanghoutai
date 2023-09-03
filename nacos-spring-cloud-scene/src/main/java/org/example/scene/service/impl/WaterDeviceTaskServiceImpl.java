package org.example.scene.service.impl;

import org.example.nacosspringcloudcommonentity.WaterDeviceTask;
import org.example.scene.dao.WaterDeviceTaskDao;
import org.example.scene.service.WaterDeviceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterDeviceTaskServiceImpl implements WaterDeviceTaskService {

    @Autowired
    private WaterDeviceTaskDao waterDeviceTaskDao;

    @Override
    public List<WaterDeviceTask> findAllTask() {
        return waterDeviceTaskDao.findAllTask();
    }
}
