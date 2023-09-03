package org.example.nacosspringcloudcommonentity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SysUser {
    public int sysUserId;
    public String account;
    public String sysUserName;
    public String password;
    public int roleId;

}
