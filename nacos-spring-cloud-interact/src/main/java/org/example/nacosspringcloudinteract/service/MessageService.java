package org.example.nacosspringcloudinteract.service;

import org.example.nacosspringcloudcommonentity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2022-06-17 15:06:27
 */
@Service
@FeignClient("message")
public interface MessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param messageId 主键
     * @return 实例对象
     */
    @GetMapping("/message/{messageId}")
    Message queryById(@PathVariable("messageId") Integer messageId);

  /*  *//**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     *//*
    Page<Message> queryByPage(Message message, PageRequest pageRequest);*/

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @PostMapping("/add")
    int insert(Message message);

  /*  *//**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     *//*
    int update(Message message);*/

    @GetMapping("/message/deleteByDid/{deviceId}")
    public int deleteByDid(@PathVariable("deviceId") String deviceId);

    @GetMapping("/message/deleteByDid/{messageId}")
    public int deleteById(@PathVariable("messageId") Integer messageId);

    /**
     * 通过设备查询多条数据
     *
     * @param deviceId
     * @return 多条数据
     */
    @GetMapping("/message/queryByDid/{deviceId}")
    public List<Message> queryByDid(@PathVariable("deviceId") String deviceId);






}
