package org.example.ydyd.dao;


import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.log.CustomerLogScore;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(CustomerDetail)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-03 17:48:28
 */
@Repository
public interface CustomerDetailDao {
    /**
     * 签到
     *
     * @param userId
     * @return int
     */
    int signIn(Integer userId);

    /**
     * 登录日志
     * 签到记录
     *
     * @param custmerLogScore 不良记录分数
     * @return int
     */
    int signInLog(CustomerLogScore custmerLogScore);

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
