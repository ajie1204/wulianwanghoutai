package org.example.nacosspringcloudprotocoldownlink.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.nacosspringcloudprotocoldownlink.entity.AboutCategory;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 关于溢爱分类(AboutCategory)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-22 16:01:49
 */
@Mapper
public interface AboutCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AboutCategory queryById(Integer id);



    /**
     * 统计总行数
     *
     * @param aboutCategory 查询条件
     * @return 总行数
     */
    long count(AboutCategory aboutCategory);

    /**
     * 新增数据
     *
     * @param aboutCategory 实例对象
     * @return 影响行数
     */
    int insert(AboutCategory aboutCategory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AboutCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AboutCategory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AboutCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AboutCategory> entities);

    /**
     * 修改数据
     *
     * @param aboutCategory 实例对象
     * @return 影响行数
     */
    int update(AboutCategory aboutCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

