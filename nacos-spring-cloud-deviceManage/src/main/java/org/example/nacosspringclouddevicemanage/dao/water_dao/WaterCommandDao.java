package org.example.nacosspringclouddevicemanage.dao.water_dao;

import com.github.pagehelper.Page;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.water.WaterCommand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterCommandDao {
    int add(DownData downData);
    //分页查询用户下发指令
    List<DownData> findByPage();
    //搜索框 userId deviceId cmd time
    List<DownData> findByBar(DownData downData);
    //删除指令记录
    void delCommand(DownData downData);
}
