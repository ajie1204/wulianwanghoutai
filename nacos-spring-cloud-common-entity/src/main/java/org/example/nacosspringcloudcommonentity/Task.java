package org.example.nacosspringcloudcommonentity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Task)实体类
 *
 * @author makejava
 * @since 2022-04-12 10:55:39
 */
@Data

public class Task implements Serializable {
    private static final long serialVersionUID = 961741324867339476L;
    /**
    * 场景任务
    */    

    private Integer taskId;

    private String taskName;
    
    /**
    * 设备码
    */    

    private String deviceId;
    
    /**
    * 指令码
    */    

    private Integer cmd;
    
    /**
    * 定时
    */    

    private String timing;

    private String taskCycle;

    
    /**
    * 任务执行顺序
    */    

    private Integer taskSequence;
    
    /**
    * 任务所属场景
    */    

    private Integer scenesId;

    private String protocol;

    private Object data;

    private String cronExpression;

    private Integer status;
}

