package org.example.scene.service.impl;

import org.example.nacosspringcloudcommonentity.Scene;
import org.example.scene.dao.SceneDao;
import org.example.scene.service.SceneService;
import org.example.scene.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Scene)表服务实现类
 *
 * @author makejava
 * @since 2022-04-11 18:51:00
 */
@Service("sceneService")
public class SceneServiceImpl implements SceneService {
    @Resource
    private SceneDao sceneDao;
    @Resource
    private Scheduler scheduler;

    /**
     * 通过ID查询单条数据
     *
     * @param scenesId 主键
     * @return 实例对象
     */
    @Override
    public Scene queryById(Integer scenesId) {
        return this.sceneDao.queryById(scenesId);
    }

    /**
     * 查询用户是否已有该场景
     * @param userId 用户id
     * @param scenesName 场景名
     * @return 实例对象
     */
    @Override
    public Scene queryByName(Integer userId, String scenesName) {
        return this.sceneDao.queryByName(userId,scenesName);
    }

    /**
     * 分页查询
     *
     * @param scene 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Scene> queryByPage(Scene scene, PageRequest pageRequest) {
        long total = this.sceneDao.count(scene);
        return new PageImpl<>(this.sceneDao.queryAllByLimit(scene, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param scene 实例对象
     * @return 实例对象
     */
    @Override
    public Scene insert(Scene scene) {
        this.sceneDao.insert(scene);
        return scene;
    }

    /**
     * 修改数据
     *
     * @param scene 实例对象
     * @return 实例对象
     */
    @Override
    public Scene update(Scene scene) {
        this.sceneDao.update(scene);
        return this.queryById(scene.getScenesId());
    }

    /**
     * 通过主键删除数据
     *
     * @param scenesId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer scenesId) {
        return this.sceneDao.deleteById(scenesId) > 0;
    }

    @Override
    public List<Scene> selectByUser(Integer userId) {
        return sceneDao.selectByUser(userId);
    }

    @Override
    public List<Scene> selectAllScenes() {
        return sceneDao.selectAllScenes();
    }


    /*@PostConstruct
    public void init() throws SchedulerException{
        scheduler.clear();
        List<Scene> sceneList = sceneDao.selectAllScenes();
        for (Scene scene:sceneList){
            ScheduleUtils.createScheduleJob(scheduler,scene);
        }
    }*/


}
