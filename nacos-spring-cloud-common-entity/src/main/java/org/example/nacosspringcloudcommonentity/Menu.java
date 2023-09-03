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
public class Menu {
    private Integer menuId;
    private String menuName;
    private Integer level;
    private Integer parentId;
    private String parentName;
    private Integer orderNum;
    private String path;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //
    private Integer userId;
    private Integer roleId;

    private Integer pageNum;
    private Integer pageSize;
}
