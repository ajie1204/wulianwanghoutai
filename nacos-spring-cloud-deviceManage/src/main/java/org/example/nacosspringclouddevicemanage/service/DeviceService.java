package org.example.nacosspringclouddevicemanage.service;

import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public interface DeviceService {



    //查找设备名字
    List<Device> selectDeviceName(Integer userId);

    String getSystemName(String deviceId);
    List<Device> findAll(int userId);
    int add(Device device);
    int updateOnline(String deviceId,int online);
    //修改设备的故障状态
    int updateMalfunction(String deviceId,int malfunction);
    List<Device> findByPage();

    //首页所需的设备信息
    List<Device> findForIndex(Integer userId);

    /*根据placeId查询设备*/
    List<Device>  selectByPlace(Integer placeId);


    /*设备和场所解绑*/
    int unbound(Integer placeId);

    /*查找用户下未绑定的设备*/
    List<Device> selectUnbound(Integer userId);

    //根据设备id查找设备
    Device findByDeviceId(String deviceId);


    int bindPlace(String deviceId,Integer placeId);

    int unboundOne(String deviceId);

    //根据搜索框查找设备 deviceId 在线状态 故障状态 激活时间 设备型号 剩余使用时间
    List<Device> findBySearchBar(Device device);
    //修改设备信息
    int updateDevice(Device device);
    //删除设备
    void delDevice(Device device);

    //激活设备
    int activateDevice(String deviceId, Integer userId,String deviceName);
    //注销设备
    int unActivate(String deviceId);
}
