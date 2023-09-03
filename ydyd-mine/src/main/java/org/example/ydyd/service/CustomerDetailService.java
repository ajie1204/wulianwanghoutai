package org.example.ydyd.service;

import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.log.CustomerLogScore;

import java.util.List;

/**
 * 客户细节服务
 *
 * @author 31477
 * @date 2023/04/06
 */
public interface CustomerDetailService {
    /**
     * 签到
     *
     * @param userId 实例对象
     * @return CustomerDetail
     */
    int signIn(Integer userId);

    /**
     * 登录日志
     * 签到记录
     *
     * @param customerLogScore 客户记录分数
     * @return int
     */
    int signInLog(CustomerLogScore customerLogScore);


    /**
     * 查用户detail
     *
     * @param userId
     * @return int
     */
    CustomerDetail getCustomerDetail(Integer userId);

    /**
     * 查签到记录
     *
     * @param userId
     * @return CustomerScore
     */
    List<CustomerLogScore> getCustomerScoreLog(Integer userId);
}
