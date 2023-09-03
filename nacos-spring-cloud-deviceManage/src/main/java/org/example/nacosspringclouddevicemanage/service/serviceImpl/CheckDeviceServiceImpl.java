package org.example.nacosspringclouddevicemanage.service.serviceImpl;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringclouddevicemanage.dao.CheckDeviceDao;
import org.example.nacosspringclouddevicemanage.service.CheckDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CheckDeviceServiceImpl implements CheckDeviceService {
    @Autowired
    private CheckDeviceDao checkDeviceDao;


    @Override
    public String check() {

        return checkDeviceDao.check();
    }

    @Override
    public int upData(Device device) {

        return checkDeviceDao.upData(device);
    }
}
