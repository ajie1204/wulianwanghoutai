package org.example.nacosspringcloudcommonentity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WaterDeviceTask {
    public Integer id;
    public Integer cmd;
    public String taskName;
}
