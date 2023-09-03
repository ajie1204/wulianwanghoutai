package org.example.nacosspringclouddevicemanage.service.serviceImpl;

import org.example.nacosspringcloudcommonentity.Device;
import org.example.nacosspringclouddevicemanage.dao.DeviceDao;
import org.example.nacosspringclouddevicemanage.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;


    @Override
    public List<Device> selectDeviceName(Integer userId) {
        return deviceDao.selectDeviceName(userId);
    }

    @Override
    public String getSystemName(String deviceId) {
        return deviceDao.getSystemName(deviceId);
    }

    @Override
    public List<Device> findAll(int userId) {
        return deviceDao.findAll(userId);
    }

    @Override
    public int add(Device device) {
        return deviceDao.add(device);
    }

    @Override
    public int updateOnline(String deviceId, int online) {
        return deviceDao.updateOnline(deviceId,online);
    }

    @Override
    public int updateMalfunction(String deviceId, int malfunction) {
        return deviceDao.updateMalfunction(deviceId,malfunction);
    }

    @Override
    public List<Device> findByPage() {
        return deviceDao.findByPage();
    }

    @Override
    public List<Device> findForIndex(Integer userId) {
        return deviceDao.findForIndex(userId);
    }
    @Override
    public List<Device> selectByPlace(Integer placeId) {
        return deviceDao.selectByPlace(placeId);
    }

    @Override
    public int unbound(Integer placeId) {
        return deviceDao.unbound(placeId);
    }

    @Override
    public List<Device> selectUnbound(Integer userId) {
        return deviceDao.selectUnbound(userId);
    }

    @Override
    public Device findByDeviceId(String deviceId) {
        return deviceDao.findByDeviceId(deviceId);
    }



    @Override
    public int bindPlace(String deviceId, Integer placeId) {
        return deviceDao.bindPlace(deviceId,placeId);
    }

    @Override
    public int unboundOne(String deviceId) {
        return deviceDao.unboundOne(deviceId);
    }

    @Override
    public List<Device> findBySearchBar(Device device) {
        return deviceDao.findBySearchBar(device);
    }

    @Override
    public int updateDevice(Device device) {
        return deviceDao.updateDevice(device);
    }

    @Override
    public void delDevice(Device device) {
        deviceDao.delDevice(device);
    }

    @Override
    public int activateDevice(String deviceId, Integer userId,String deviceName) {
        return deviceDao.activateDevice(deviceId,userId,deviceName);
    }
    @Override
    public int unActivate(String deviceId) {
        return deviceDao.unActivate(deviceId);
    }

}
