package org.example.deviceAuth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 设备入网注册参数
 *
 * @author makejava
 * @since 2022-01-19 11:05:16
 */
@Data
public class RegistInfo implements Serializable {


    /**
     * 厂商
     */
    private String producer;
    /**
     * 生产时间
     */
    @JSONField(format="yyyy/MM/dd HH:mm:ss")
    private Date time;
    /**
     * 生产地址
     */
    private String address;

    /**
     * 批次
     */
    private int batch;
    /**
     * 设备类型
     */
    private String type;

    /**
     * 型号
     */
    private String model;

    /**
     * 协议
     */
    private String protocol;






}

