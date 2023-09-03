package org.example.nacosspringcloudcommonentity.water;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class WaterCommand {
    public Integer id;
    public Integer userId;
    public String deviceId;
    public Integer cmd;
    public String data;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date time;
    public String serialNum;
    public String protocol;

    public Date startTime;
    public Date endTime;
    public Integer pageNum;
    public Integer pageSize;

}
