package org.example.nacosspringcloudprotocoldownlink.service.impl;

import org.example.nacosspringcloudprotocoldownlink.entity.AboutCategory;
import org.example.nacosspringcloudprotocoldownlink.dao.AboutCategoryDao;
import org.example.nacosspringcloudprotocoldownlink.service.AboutCategoryService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * 关于溢爱分类(AboutCategory)表服务实现类
 *
 * @author makejava
 * @since 2022-02-22 16:01:51
 */
@Service("aboutCategoryService")
public class AboutCategoryServiceImpl implements AboutCategoryService {
    @Resource
    private AboutCategoryDao aboutCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AboutCategory queryById(Integer id) {
        return this.aboutCategoryDao.queryById(id);
    }




    /**
     * 新增数据
     *
     * @param aboutCategory 实例对象
     * @return 实例对象
     */
    @Override
    public AboutCategory insert(AboutCategory aboutCategory) {
        this.aboutCategoryDao.insert(aboutCategory);
        return aboutCategory;
    }

    /**
     * 修改数据
     *
     * @param aboutCategory 实例对象
     * @return 实例对象
     */
    @Override
    public AboutCategory update(AboutCategory aboutCategory) {
        this.aboutCategoryDao.update(aboutCategory);
        return this.queryById(aboutCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.aboutCategoryDao.deleteById(id) > 0;
    }
}
