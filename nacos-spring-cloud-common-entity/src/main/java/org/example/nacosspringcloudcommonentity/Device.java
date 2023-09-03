package org.example.nacosspringcloudcommonentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Device {
    public String deviceId;
    public String deviceName;
    //设备在线状态
    public Integer online;
    public Integer malfunction;
    public Integer systemId;
    public Integer placeId;
    public Integer userId;
    public Integer picturesId;
    //激活时间
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date activationTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date remainsTime;
    //剩余使用时间
    public Integer remains;
    //设备型号
    public String type;
    //
    public String protocol;

    //查询变量
    public String account;

    //不计入数据库，做时间段查询
    public Date activeStartTime;
    public Date activeEndTime;
    public Date remainStartTime;
    public Date remainEndTime;
    public Integer pageNum;
    public Integer pageSize;

}
