package org.example.nacosspringcloudcommonentity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2022-06-17 15:06:27
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -33586769011435968L;
    /**
     * 消息id
     */
    private Integer messageId;
    /**
     * 设备码
     */
    private String deviceId;
    /**
     * 消息内存
     */
    private String context;
    /**
     * 消息是否已读
     */
    private Integer readStatus;
    /**
     * 消息是否为故障信息
     */
    private Integer malfunction;
    /**
     * 时间
     */
    private Date time;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getMalfunction() {
        return malfunction;
    }

    public void setMalfunction(Integer malfunction) {
        this.malfunction = malfunction;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}

