package org.example.nacosspringcloudcommonentity;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class UserOrder implements Serializable {
    private Integer userId;
    private String deviceId;
    private Integer cmd;
    private String data;
    private Object jsonData;

}
