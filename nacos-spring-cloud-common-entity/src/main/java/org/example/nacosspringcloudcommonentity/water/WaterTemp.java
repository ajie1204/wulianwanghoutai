package org.example.nacosspringcloudcommonentity.water;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class WaterTemp {
    public Integer id;
    public String deviceId;
    public Integer curTemp;
    public Date time;
}
