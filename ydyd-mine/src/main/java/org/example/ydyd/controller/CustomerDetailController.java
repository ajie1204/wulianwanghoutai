package org.example.ydyd.controller;

import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.CustomerScore;

import org.example.nacosspringcloudcommonentity.customer.log.CustomerLogScore;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.ydyd.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 客户详细控制器
 *
 * @author 31477
 * @date 2023/04/06
 */

@RestController
@RequestMapping("customerdetail")
public class CustomerDetailController {


    /**
     * 客户细节服务
     */
    @Autowired
    CustomerDetailService customerDetailService;
    


    /**
     * 签到
     *
     * @param customerDetail 客户详细信息
     * @return {@link Response}<{@link CustomerDetail}>
     */
    @PostMapping("/score/signin")
    public Response<CustomerDetail> signIn(@RequestBody CustomerDetail customerDetail){
        Integer userId = customerDetail.getUserId();
        Date d = new Date();
        CustomerLogScore customerLogScore=new CustomerLogScore();
        customerLogScore.setUnid(userId);
        customerLogScore.setIntegral(2);
        customerLogScore.setCreatedAt(d);
        customerLogScore.setDesc("签到奖励");
        customerLogScore.setStatus(1);
        customerLogScore.setAdminId(0);

        if(customerDetailService.signIn(userId)>0&&customerDetailService.signInLog(customerLogScore)>0){
            return Response.createSuccessResponse(customerDetailService.getCustomerDetail(userId));
        }else{
            return Response.createErrorResponse("签到失败",customerDetailService.getCustomerDetail(userId));
        }

    }

    /**
     * score日志
     *
     * @param userId 用户id
     * @return {@link Response}<{@link List}<{@link CustomerScore}>>
     */
    @GetMapping("/score/log/{userId}")
    public Response<List<CustomerLogScore>> signInLog(@PathVariable Integer userId){

        return Response.createSuccessResponse(customerDetailService.getCustomerScoreLog(userId));
    }

}
