package org.example.message.controller;

import org.example.message.service.WebsocketService;
import org.example.nacosspringcloudcommonentity.Message;
import org.example.message.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2022-06-17 15:06:27
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    @Resource
    WebsocketService websocketService;

    /**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public Page<Message> queryByPage(Message message, PageRequest pageRequest) {
        return this.messageService.queryByPage(message, pageRequest);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public Message queryById(@PathVariable("id") Integer id) {
        return this.messageService.queryById(id);
    }

    /**
     * 通过设备查询多条数据
     *
     * @param deviceId
     * @return 多条数据
     */
    @GetMapping("/queryByDid/{deviceId}")
    public List<Message> queryByDid(@PathVariable("deviceId") String deviceId) {
        return this.messageService.queryByDid(deviceId);
    }

    /**
     * 新增数据
     *
     * @param message 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public int add(@RequestBody Message message) {
        int id = this.messageService.insert(message);
        return id;
    }

    /**
     * 编辑数据
     *
     * @param message 实体
     * @return 编辑结果
     */
    @PutMapping
    public int edit(Message message) {
        return this.messageService.update(message);
    }

    /**
     * 删除数据
     *
     * @param deviceId 主键
     * @return 删除是否成功
     */
    @GetMapping("/deleteByDid/{deviceId}")
    public int deleteByDid(@PathVariable("deviceId") String deviceId) {
        return this.messageService.deleteByDid(deviceId);
    }

    @GetMapping("/deleteByDid/{messageId}")
    public int deleteById(@PathVariable("messageId") Integer messageId) {
        return this.messageService.deleteById(messageId);
    }

}

