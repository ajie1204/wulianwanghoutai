package org.example.nacosspringcloudcommonentity.water;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

/**
 * 此类包含滤芯以及饮水机的剩余使用天数
 * 方法一
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class WaterFilter {

    @JsonProperty("last(deviceId)")
    public String deviceId;
    @JsonProperty("last(rech_days)")
    public Integer remainder;
    public Integer PP;
    public Integer GAC;
    public Integer RO;
    public Integer T33;
    public Date time;
}
