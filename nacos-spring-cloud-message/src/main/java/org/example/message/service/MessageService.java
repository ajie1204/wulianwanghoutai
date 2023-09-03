package org.example.message.service;

import org.example.nacosspringcloudcommonentity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2022-06-17 15:06:27
 */
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param messageId 主键
     * @return 实例对象
     */
    Message queryById(Integer messageId);

    /**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Message> queryByPage(Message message, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    int insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    int update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param deviceId
     * @return 是否成功
     */
    int deleteByDid(String deviceId);


    int deleteById(Integer messageId);


    List<Message> queryByDid(String deviceId);
}
