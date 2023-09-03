package org.example.nacosspringclouddevicemanage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Device {
    private String deviceId;
    private String deviceName;
    private Integer deviceStatus;
    private Integer deviceMalfunction;
    private Integer systemId;
    private Integer placeId;
    private Integer userId;
    private Integer devicePicId;

}
