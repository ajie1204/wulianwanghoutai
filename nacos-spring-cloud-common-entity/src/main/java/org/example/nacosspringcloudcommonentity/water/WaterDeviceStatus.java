package org.example.nacosspringcloudcommonentity.water;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 饮水机的各个功能或者开关状态
 * 是否待机
 * 是否加热
 * 是否制水
 * 童锁
 * 设定的温度
 * 设备缺水
 * 设备漏水
 * 在线状态
 * 剩余使用天数
 * 信号强度
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class WaterDeviceStatus {
    public Integer id;
    public String deviceId;
    public Integer standby;
    public Integer heat;
    public Integer waterMaking;
    public Integer childLock;
    public Float maxTemp;
    public Integer lacking;
    public Integer leaking;
    public Integer online;
    public Integer csq;
    public Date time;
    public Integer hotWater;
    public Integer coldWater;
    public Integer washing;
    public Integer checking;
}
