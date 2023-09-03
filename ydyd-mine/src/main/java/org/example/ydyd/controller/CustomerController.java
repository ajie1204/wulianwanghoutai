package org.example.ydyd.controller;

import org.example.nacosspringcloudcommonentity.constant.Const;
import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.UserLevel;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.ydyd.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 客户控制器
 * 用户表(Customer)表控制层
 *
 * @author makejava
 * @date 2023/04/12
 * @since 2023-04-03 17:48:24
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    /**
     * 客户服务
     */
    @Resource
    CustomerService customerService;

    /**
     * 得到客户
     *
     * @param mobile 移动
     * @return {@link Response}<{@link Customer}>
     */
    @GetMapping("/getcustomer/{mobile}")
    public Response<Customer> getCustomer(@PathVariable String mobile){

        return Response.createSuccessResponse(customerService.queryByMobile(mobile));
    }

    /**
     * 得到客户级别
     *
     * @param mobile 移动
     * @return {@link Response}<{@link UserLevel}>
     */
    @GetMapping("/getcustomerlevel/{mobile}")
    public Response<UserLevel> getCustomerLevel(@PathVariable String mobile){
        return Response.createSuccessResponse(customerService.queryCustomerLevelByMobile(mobile));

    }

    /**
     * 得到客户细节
     *
     * @param mobile 移动
     * @return {@link Response}<{@link CustomerDetail}>
     */
    @GetMapping("/getcustomerdetail/{mobile}")
    public Response<CustomerDetail> getCustomerDetail(@PathVariable String mobile){
        return Response.createSuccessResponse(customerService.queryCustomerDetailByMobile(mobile));
    }

    /**
     * 让客户通过openid
     *
     * @param openid openid
     * @return {@link Customer}
     */
    @GetMapping("/getcustomerbyopneid/{openid}")
    public Customer getCustomerByOpenid(@PathVariable String openid){
        return customerService.queryByOpenid(openid);
    }

    /**
     * 插入客户
     *
     * @param customer 客户
     * @return {@link Customer}
     */
    @PostMapping("/addcustomer")
    public Customer insertCustomer(@RequestBody Customer customer){
        return customerService.insert(customer);
    }

    /**
     * 被请求客户代码
     *
     * @param requestCode 请求代码
     * @return {@link Customer}
     */
    @GetMapping("/getcustomerbyrequest/{requestCode}")
    public Customer getCustomerByRequestCode(@PathVariable String requestCode){
        return customerService.queryByRequestCode(requestCode);
    }

    /**
     * 得到第一个父
     *
     * @param firstParent 第一个父
     * @return {@link Customer}
     */
    @GetMapping("/getfirstparent/{firstParent}")
    public Customer getFirstParent(@PathVariable Integer firstParent){
        return customerService.queryFirstParent(firstParent);
    }




}

