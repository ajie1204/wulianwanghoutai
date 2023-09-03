package org.example.nacosspringcloudcommonentity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 下传指令帧封装类
 *
 * @author makejava
 * @since 2022-01-19 11:05:16
 */
@Data
public class DownCommand implements Serializable {
    private static final long serialVersionUID = -88225776927579517L;
    /**
     * 指令码
     */
    private Integer cmd;
    /**
     * 指令内容
     * private String data;
     */
    private String data;
    /**
     * 时间
     */
    private String time;

    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 指令序列号
     */
    private String serialNumber;




}

