package org.example.nacosspringcloudcommonentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginLog {
    public Integer id;
    public String account;
    //登录状态、成功或者失败
    public String status;
    public String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date loginTime;

    public Date startTime;
    public Date endTime;
    public Integer pageNum;
    public Integer pageSize;
}
