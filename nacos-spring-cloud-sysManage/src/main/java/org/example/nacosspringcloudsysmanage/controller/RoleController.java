package org.example.nacosspringcloudsysmanage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nacosspringcloudcommonentity.Role;
import org.example.nacosspringcloudsysmanage.service.RoleAndMenuService;
import org.example.nacosspringcloudsysmanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAndMenuService roleAndMenuService;


    /**
     * 新增角色
     */
    @PostMapping("add")
    public Map<String,Object> add(@RequestBody Role role){
        role.setRoleTime(new Date());
        Map<String,Object> map = new HashMap<>();
        Role role1 = roleService.find(role.getRoleName());
        if (role1==null){
            roleService.add(role);
            map.put("result","添加成功");
            map.put("list",roleService.findAll());
        }else {
            map.put("result","添加失败，该角色已存在");
        }
        /*PageHelper.startPage(role.getPageNum(),role.getPageSize());*/
        List<Role> roleList = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("roleList",pageInfo.getList());
        return map;
    }


    /**
     * 查找所有角色
     */
    @GetMapping("/getRoleList/{pageNum}/{pageSize}")
    public Map<String,Object> findAll(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roleList = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("roleList",pageInfo.getList());
        return map;
    }


    /**
     * 删除角色
     */
    @PostMapping("/delRole")
    public Map<String,Object> delRole(@RequestBody Role role){
        Map<String,Object> map = new HashMap<>();
        roleService.del(role);
        roleAndMenuService.delByRoleId(role.getRoleId());
        map.put("result","删除成功");
//        PageHelper.startPage(role.getPageNum(),role.getPageSize());
        List<Role> roleList = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("roleList",pageInfo.getList());
        return map;
    }


    @PostMapping("/updateRole")
    public Map<String,Object> updateRole(@RequestBody Role role){
        Map<String,Object> map = new HashMap<>();
        roleService.updateRole(role);
        map.put("result","修改成功");
//        PageHelper.startPage(role.getPageNum(),role.getPageSize());
        List<Role> roleList = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("roleList",pageInfo.getList());
        return map;
    }
}
