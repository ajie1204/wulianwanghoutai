package org.example.scene.service.impl;
 
import org.example.nacosspringcloudcommonentity.Task;
import org.example.scene.dao.TaskDao;
import org.example.scene.service.TaskService;
import org.springframework.stereotype.Service;
 
import javax.annotation.Resource;
import java.util.List;
 
/**
 * (Task)表服务实现类
 *
 * @author makejava
 * @since 2022-04-12 10:55:49
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskDao taskDao;
 
    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    @Override
    public Task selectById(Integer taskId) {
        return this.taskDao.selectById(taskId);
    }
 
    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Task> selectPage(int start, int limit) {
        return this.taskDao.selectPage(start, limit);
    }
    
    /**
     * 查询全部
     *
     * @return 对象列表
     */
    @Override
    public List<Task> selectAll() {
        return this.taskDao.selectAll();
    }
 
    /**
     * 根据实体类进行筛选
     
     * @param task 实例对象
     * @return 对象列表
     */
    @Override
    public List<Task> selectList(Task task) {
        return this.taskDao.selectList(task);
    }
    
    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 生效条数
     */
    @Override
    public int insert(Task task) {
        return this.taskDao.insert(task);
    }
    
    /**
     * 批量新增
     *
     * @param taskList 实例对象
     * @return 生效条数
     */
    @Override
    public int batchInsert(List<Task> taskList) {
        return this.taskDao.batchInsert(taskList);
    }
    
 
    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Task task) {
        return this.taskDao.update(task);
    }
 
    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer taskId) {
        return this.taskDao.deleteById(taskId);
    }

    @Override
    public int deleteByScenesId(Integer scenesId) {
        return taskDao.deleteByScenesId(scenesId);
    }

    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count() {
        return this.taskDao.count();
    }

    @Override
    public List<Task> selectTaskList(Integer scenesId) {
        return taskDao.selectTaskList(scenesId);
    }


}
 


