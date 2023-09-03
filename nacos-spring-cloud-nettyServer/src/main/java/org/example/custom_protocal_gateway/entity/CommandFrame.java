package org.example.custom_protocal_gateway.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 下行给设备的完整帧的封装类
 *
 * @author makejava
 * @since 2022-01-19 11:05:16
 */
@Data
public class CommandFrame implements Serializable {
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 指令序列号
     */
    private String serialNumber;




}

