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
public class User {
    public Integer userId;
    public String account;
    public String password;
    public String userName;
    public String address;
    public Integer character;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date loginTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date registerTime;
    public Integer roleId;
    //下面数据不进入数据库
    //token数据
    public String token;
    public Date expireTime;
    //根据时间查询
    public Date startTime;
    public Date endTime;

    public Integer pageNum;
    public Integer pageSize;




}
