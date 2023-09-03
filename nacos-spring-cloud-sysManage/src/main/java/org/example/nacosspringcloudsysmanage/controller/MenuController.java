package org.example.nacosspringcloudsysmanage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nacosspringcloudcommonentity.Menu;
import org.example.nacosspringcloudsysmanage.service.MenuService;
import org.example.nacosspringcloudsysmanage.service.RoleAndMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleAndMenuService roleAndMenuService;

    //获得全部的菜单
    @GetMapping("/getAllMenu/{pageNum}/{pageSize}")
    public Map<String,Object> getAll(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        Map<String,Object> map  = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Menu> menuList = menuService.findAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menuList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("menuList",pageInfo.getList());
        return map;
    }


    @PostMapping("/addMenu")
    public Map<String,Object> addMenu(@RequestBody Menu menu){
        Map<String,Object> map  = new HashMap<>();
        menuService.add(menu);
        map.put("result","新增成功");
//        PageHelper.startPage(menu.getPageNum(),menu.getPageSize());
        List<Menu> menuList = menuService.findAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menuList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("menuList",pageInfo.getList());
        return map;
    }


    @PostMapping("/updateMenu")
    public Map<String,Object> updateMenu(@RequestBody Menu menu){
        Map<String,Object> map  = new HashMap<>();
        menuService.updateMenu(menu);
        map.put("result","修改成功");
//        PageHelper.startPage(menu.getPageNum(),menu.getPageSize());
        List<Menu> menuList = menuService.findAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menuList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("menuList",pageInfo.getList());
        return map;
    }


    @PostMapping ("/delMenu")
    public Map<String,Object> delMenu(@RequestBody Menu menu){
        Map<String,Object> map  = new HashMap<>();
        menuService.delMenu(menu);
        roleAndMenuService.delByMenuId(menu.getMenuId());
        map.put("result","删除成功");
//        PageHelper.startPage(menu.getPageNum(),menu.getPageSize());
        List<Menu> menuList = menuService.findAll();
        PageInfo<Menu> pageInfo = new PageInfo<>(menuList);
        map.put("总页数",pageInfo.getPages());
        map.put("总记录数",pageInfo.getTotal());
        map.put("当前页数",pageInfo.getPageNum());
        map.put("当前页面记录数",pageInfo.getSize());
        map.put("menuList",pageInfo.getList());
        return map;
    }
}
