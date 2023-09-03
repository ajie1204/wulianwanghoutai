package org.example.nacosspringcloudcommonentity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class DownData implements Serializable {
    //private Integer id;
    private Integer userId;

    private String deviceId;
    private Integer cmd;
    private String data;
    private Date time;
    private String serialNumber;

    private String protocol;
    private JSONObject jsonData;
    public Integer pageNum;
    public Integer pageSize;

    private String username = "admin";
    private String password = "admin123";

    private String message;
    private Integer qos = 0;
    private String topic = "/v1/devices/001/command";
    private boolean retain = false;




}
