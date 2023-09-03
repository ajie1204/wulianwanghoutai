package org.example.nacosspringcloudprotocoldownlink.service;

import org.example.nacosspringcloudprotocoldownlink.entity.AboutCategory;


/**
 * 关于溢爱分类(AboutCategory)表服务接口
 *
 * @author makejava
 * @since 2022-02-22 16:01:51
 */
public interface AboutCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AboutCategory queryById(Integer id);



    /**
     * 新增数据
     *
     * @param aboutCategory 实例对象
     * @return 实例对象
     */
    AboutCategory insert(AboutCategory aboutCategory);

    /**
     * 修改数据
     *
     * @param aboutCategory 实例对象
     * @return 实例对象
     */
    AboutCategory update(AboutCategory aboutCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
