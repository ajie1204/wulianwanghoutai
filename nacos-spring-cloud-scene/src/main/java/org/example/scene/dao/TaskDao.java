package org.example.scene.dao;
 
import org.example.nacosspringcloudcommonentity.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Task)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-12 10:55:39
 */
@Repository
public interface TaskDao {
 
    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    Task selectById(Integer taskId);
	
    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Task> selectPage(@Param("start") int start, @Param("limit") int limit);
 
    /**
     * 查询全部
     *
     * @return 对象列表
     */
    List<Task> selectAll();
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param task 实例对象
     * @return 对象列表
     */
    List<Task> selectList(Task task);
 
    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 影响行数
     */
    int insert(Task task);
	
	/**
     * 批量新增
     *
     * @param taskList 实例对象的集合
     * @return 影响行数
     */
     int batchInsert(List<Task> taskList);
	
    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 影响行数
     */
    int update(Task task);
 
    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 影响行数
     */
    int deleteById(Integer taskId);

    /**
     * 删除场景id下的所有任务
     * @param scenesId 场景id
     * @return
     */
    int deleteByScenesId(Integer scenesId);
 
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    int count();

    /**
     * 查询场景下的任务
     * @param scenesId 场景关联字段
     * @return 任务列表
     */
    List<Task> selectTaskList(Integer scenesId);


}
 
 


