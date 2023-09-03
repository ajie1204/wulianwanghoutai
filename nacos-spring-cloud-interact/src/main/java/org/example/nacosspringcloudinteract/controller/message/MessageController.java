package org.example.nacosspringcloudinteract.controller.message;



import org.example.nacosspringcloudcommonentity.Message;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.nacosspringcloudinteract.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    MessageService messageService;

  /*  *//**
     * 分页查询
     *
     * @param message 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     *//*
    @GetMapping
    public Response<Page<Message>> queryByPage(Message message, PageRequest pageRequest) {
        return Response.createSuccessResponse(this.messageService.queryByPage(message, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<Message> queryById(@PathVariable("id") Integer id) {
        return Response.createSuccessResponse(this.messageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param message 实体
     * @return 新增结果
     */
    @PostMapping
    public Response<String> add(Message message) {
        int result = this.messageService.insert(message);
        if (result>0){
            return Response.createSuccessResponse("新增消息成功");
        }
            return Response.createErrorResponse("新增失败");

    }

   /* *//**
     * 编辑数据
     *
     * @param message 实体
     * @return 编辑结果
     *//*
    @PutMapping
    public Response<Message> edit(Message message) {
        return ;
    }*/

    /**
     * 删除数据
     *
     * @param deviceId 设备id
     * @return 删除是否成功
     */
    @DeleteMapping
    public Response<String> deleteById(Integer deviceId) {
        if (this.messageService.deleteById(deviceId)==1){
            return  Response.createSuccessResponse("删除成功");
        }
            return Response.createErrorResponse("删除失败");
    }

}

