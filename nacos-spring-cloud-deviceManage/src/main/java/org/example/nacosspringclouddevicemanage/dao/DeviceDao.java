package org.example.nacosspringclouddevicemanage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceDao {

    //查找设备名字
    List<Device> selectDeviceName(Integer userId);
    //查找设备所属系统,两表联查
    String getSystemName(String deviceId);
    //查询用户所有的设备
    List<Device> findAll(Integer userId);
    //修改设备的在线状态
    int updateOnline(String deviceId,int online);
    //修改设备的故障状态
    int updateMalfunction(String deviceId,int malfunction);
    //查询所有的设备
    List<Device> findByPage();
    //新增设备
    int add(Device device);
    //首页所需的设备信息
    List<Device> findForIndex(Integer userId);
    //根据设备id查找设备
    Device findByDeviceId(String deviceId);



    //场景下添加设备
    //场所下删除设备

    List<Device> selectByPlace(Integer placeId);

    int unbound(Integer placeId);

    List<Device> selectUnbound(Integer userId);

    int bindPlace(String deviceId, Integer placeId);

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
