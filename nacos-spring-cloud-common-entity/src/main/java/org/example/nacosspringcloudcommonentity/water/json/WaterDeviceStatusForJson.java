package org.example.nacosspringcloudcommonentity.water.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class WaterDeviceStatusForJson {


    //实时温度
    @JsonProperty("last(temperature)")
    public Integer temperature;

    //原水净水tds
    @JsonProperty("last(old_tds)")
    public Float old_tds;

    @JsonProperty("last(new_tds)")
    public Float new_tds;

    // 设备状态
    @JsonProperty("last(device_status)")
    public Integer standby;

    @JsonProperty("last(watermaking)")
    public Integer waterMaking;

    @JsonProperty("last(lacking)")
    public Integer lacking;

    @JsonProperty("last(leaking)")
    public Integer leaking;

    @JsonProperty("last(childlock)")
    public Integer childLock;

    @JsonProperty("last(hotwater)")
    public Integer hotWater;

    @JsonProperty("last(coldwater)")
    public Integer coldWater;

    @JsonProperty("last(washing)")
    public Integer washing;

    @JsonProperty("last(checking)")
    public Integer checking;

    //加热开关
    @JsonProperty("last(heat)")
    public Integer heat;

    //最高温度
    @JsonProperty("last(maxtemp)")
    private float maxTemp;

    // 剩余天数
    @JsonProperty("last(rech_days)")
    public Integer remainder;

    public Integer PP;
    public Integer GAC;
    public Integer RO;
    public Integer T33;

    // 设备id
    @JsonProperty("last(deviceId)")
    public String deviceId;

    @JsonProperty("last(event_time)")
    public String event_time;

    @JsonProperty("last(filter_lifes)")
    public String rech_days;

    @JsonProperty("last(ts)")
    public String ts;

}
