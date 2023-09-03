package org.example.scene.dao;

import org.example.nacosspringcloudcommonentity.WaterDeviceTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterDeviceTaskDao {
    List<WaterDeviceTask> findAllTask();
}
