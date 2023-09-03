package org.example.nacosspringcloudcommonentity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色 菜单关联表
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleAndMenu {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
}
