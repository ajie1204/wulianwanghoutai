package org.example.message.service.impl;

import org.example.nacosspringcloudcommonentity.Message;
import org.example.message.dao.MessageDao;
import org.example.message.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2022-06-17 15:06:27
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageDao messageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param messageId 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(Integer messageId) {
        return this.messageDao.queryById(messageId);
    }

    /**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Message> queryByPage(Message message, PageRequest pageRequest) {
        long total = this.messageDao.count(message);
        return new PageImpl<>(this.messageDao.queryAllByLimit(message, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Message message) {

        return this.messageDao.insert(message);
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Message message) {

        return this.messageDao.update(message);
    }

    /**
     * @param deviceId
     * @return 是否成功
     */
    @Override
    public int deleteByDid(String deviceId) {
        return this.messageDao.deleteByDid(deviceId);
    }

    @Override
    public int deleteById(Integer messageId) {
        return messageDao.deleteById(messageId);
    }

    @Override
    public List<Message> queryByDid(String deviceId) {
        return messageDao.queryByDid(deviceId);
    }
}
