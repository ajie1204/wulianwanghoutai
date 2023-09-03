package org.example.ydyd.controller;

import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.ydyd.service.CustomerService;
import org.example.ydyd.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 31477
 */
@RestController
@RequestMapping("vip")
public class VipController {

    @Autowired
    private VipService vipService;

    @PostMapping("/registeredvip")
    public Response<Customer> registeredVip(@RequestBody Customer customer){
        if(vipService.registeredVip(customer.getId())>0){
            return Response.createSuccessResponse("开通成功",customer);
        }else {
            return Response.createErrorResponse("开通失败");
        }
    }

    

}
