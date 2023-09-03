package org.example.scene.service;
 
import org.example.nacosspringcloudcommonentity.Task;
import java.util.List;

/**
 * (Task)表服务接口
 *
 * @author makejava
 * @since 2022-04-12 10:55:49
 */
public interface TaskService {
 
    /**
     * 通过ID查询单条数据
     */
    Task selectById(Integer taskId);
 
    /**
     * 分页查询
     */
    List<Task> selectPage(int start, int limit);
 
    /**
     * 查询全部
     */
    List<Task> selectAll();
    
    /**
     * 通过实体作为筛选条件查询
     */
    List<Task> selectList(Task task);
 
    /**
     * 新增数据
     */
    int insert(Task task);
	
	/**
     * 批量新增
     */
    int batchInsert(List<Task> tasks);
	
    /**
     * 修改数据
     */
    int update(Task task);
 
    /**
     * 通过主键删除数据
     */
    int deleteById(Integer taskId);

    int deleteByScenesId(Integer scenesId);
    
    /**
     * 查询总数据数
     */
    int count();

    /**
     * 查询场景下的任务
     * @param scenesId 场景关联字段
     * @return 任务列表
     */
    List<Task> selectTaskList(Integer scenesId);




}

