package org.example.nacosspringcloudcommonentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role {
    private Integer roleId;
    private String roleName;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date roleTime;
    //权限字符
    private String roleChar;

    private Integer pageNum;
    private Integer pageSize;
}
