package org.example.nacosspringclouddevicemanage.service.water_service.water_serviceImpl;


import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.water.WaterCommand;
import org.example.nacosspringclouddevicemanage.dao.water_dao.WaterCommandDao;
import org.example.nacosspringclouddevicemanage.service.water_service.WaterCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterCommandServiceImpl implements WaterCommandService {
    @Autowired
    private WaterCommandDao waterCommandDao;

    @Override
    public int add(DownData downData) {
        return waterCommandDao.add(downData);
    }

    @Override
    public List<DownData> findByPage() {
        return waterCommandDao.findByPage();
    }

    @Override
    public List<DownData> findByBar(DownData downData) {
        return waterCommandDao.findByBar(downData);
    }

    @Override
    public void delCommand(DownData downData) {
        waterCommandDao.delCommand(downData);
    }


}
