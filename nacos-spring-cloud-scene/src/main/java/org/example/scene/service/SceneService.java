package org.example.scene.service;

import org.example.nacosspringcloudcommonentity.Scene;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Scene)表服务接口
 *
 * @author makejava
 * @since 2022-04-11 18:51:00
 */
public interface SceneService {

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
     * 分页查询
     *
     * @param scene 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Scene> queryByPage(Scene scene, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param scene 实例对象
     * @return 实例对象
     */
    Scene insert(Scene scene);

    /**
     * 修改数据
     *
     * @param scene 实例对象
     * @return 实例对象
     */
    Scene update(Scene scene);

    /**
     * 通过主键删除数据
     *
     * @param scenesId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer scenesId);


    List<Scene> selectByUser(Integer userId);

    List<Scene> selectAllScenes();

}
