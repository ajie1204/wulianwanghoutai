package org.example.nacosspringcloudcommonentity;

import lombok.Data;

import javax.lang.model.element.NestingKind;
import java.io.Serializable;

@Data
public class UplinkCommand implements Serializable {
    private String system;
    private String deviceID;
    private Integer upload_code;
    private String data;
    private String time;

}
