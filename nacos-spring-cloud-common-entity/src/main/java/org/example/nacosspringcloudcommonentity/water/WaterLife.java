package org.example.nacosspringcloudcommonentity.water;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//滤芯与剩余使用时间类
@Getter
@Setter
@ToString
@NoArgsConstructor
public class WaterLife {
    public Integer remainder;
    public Integer pp_life;
    public Integer gac_life;
    public Integer ro_life;
    public Integer t33_life;
}
