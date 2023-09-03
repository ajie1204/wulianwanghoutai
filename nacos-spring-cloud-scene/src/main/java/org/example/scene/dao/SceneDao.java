package org.example.scene.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.nacosspringcloudcommonentity.Scene;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Scene)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-11 18:50:59
 */
@Repository
public interface SceneDao {

     List<Scene> selectByUser(Integer userId);

    /**
     * 通过ID查询单条数据
     *
     * @param scenesId 主键
     * @return 实例对象
     */
    Scene queryById(Integer scenesId);

    /**
     * 查询用户是否已有该场景
     * @param userId 用户id
     * @param scenesName 场景名
     * @return 实例对象
     */
    Scene queryByName(Integer userId,String scenesName);


    /**
     * 查询指定行数据
     *
     * @param scene 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Scene> queryAllByLimit(Scene scene, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param scene 查询条件
     * @return 总行数
     */
    long count(Scene scene);

    /**
     * 新增数据
     *
     * @param scene 实例对象
     * @return 影响行数
     */
    int insert(Scene scene);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Scene> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Scene> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Scene> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Scene> entities);

    /**
     * 修改数据
     *
     * @param scene 实例对象
     * @return 影响行数
     */
    int update(Scene scene);

    /**
     * 通过主键删除数据
     *
     * @param scenesId 主键
     * @return 影响行数
     */
    int deleteById(Integer scenesId);

    List<Scene> selectAllScenes();

    int updateStatus(Integer status);

}

