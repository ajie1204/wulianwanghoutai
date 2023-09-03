package org.example.ydyd.service.impl;

import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.CustomerScore;
import org.example.nacosspringcloudcommonentity.customer.log.CustomerLogScore;
import org.example.ydyd.dao.CustomerDetailDao;
import org.example.ydyd.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户细节服务impl
 *
 * @author 31477
 * @date 2023/04/06
 */
@Service("customerDetailService")
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    CustomerDetailDao customerDetailDao;

    /**
     * 签到
     *
     * @param userId 实例对象
     * @return CustomerDetail
     */
    @Override
    public int signIn(Integer userId) {
        return customerDetailDao.signIn(userId);
    }

    /**
     * 登录日志
     * 签到记录
     *
     * @param customerLogScore 客户记录分数
     * @return int
     */
    @Override
    public int signInLog(CustomerLogScore customerLogScore) {
        return customerDetailDao.signInLog(customerLogScore);
    }


    /**
     * 查用户detail
     *
     * @param userId
     * @return int
     */
    @Override
    public CustomerDetail getCustomerDetail(Integer userId) {
        return customerDetailDao.getCustomerDetail(userId);
    }

    /**
     * 查签到记录
     *
     * @param userId
     * @return CustomerScore
     */
    @Override
    public List<CustomerLogScore> getCustomerScoreLog(Integer userId) {
        return customerDetailDao.getCustomerScoreLog(userId);
    }


}
