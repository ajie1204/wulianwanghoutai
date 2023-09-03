package org.example.nacosspringclouddevicemanage.dao;


import org.apache.ibatis.annotations.Mapper;
import org.example.nacosspringcloudcommonentity.Device;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface CheckDeviceDao {
    String check ();
    int upData (Device device);
}
