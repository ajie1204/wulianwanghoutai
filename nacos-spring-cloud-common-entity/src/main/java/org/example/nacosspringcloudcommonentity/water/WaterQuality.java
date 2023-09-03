package org.example.nacosspringcloudcommonentity.water;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
@Data
public class WaterQuality {
    public Integer id;
    public String deviceId;
    public Float raw_water;
    public Float clean_water;
    public Date time;
}
