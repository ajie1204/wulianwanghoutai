package org.example.ydyd.service.impl;

import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.UserLevel;
import org.example.ydyd.dao.CustomerDao;
import org.example.ydyd.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户服务impl
 * 用户表(Customer)表服务实现类
 *
 * @author makejava
 * @date 2023/04/06
 * @since 2023-04-03 17:48:30
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    /**
     *
     */
    @Resource
    private CustomerDao customerDao;

    /**
     * 查询通过id
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Customer queryById(Integer id) {
        return this.customerDao.queryById(id);
    }

    /**
     * 查询手机
     *
     * @param mobile 移动
     * @return {@link Customer}
     */
    @Override
    public Customer queryByMobile(String mobile) {
        return customerDao.queryByMobile(mobile);
    }


    /**
     * 通过手机查询客户水平
     * 通过手机号查询等级
     *
     * @param mobile 移动
     * @return {@link UserLevel}
     */
    @Override
    public UserLevel queryCustomerLevelByMobile(String mobile) {
        return customerDao.queryCustomerLevelByMobile(mobile);
    }

    /**
     * 通过手机号查询用户细节
     *
     * @param mobile 移动
     * @return {@link CustomerDetail}
     */

    @Override
    public CustomerDetail queryCustomerDetailByMobile(String mobile) {
        return customerDao.queryCustomerDetailByMobile(mobile);
    }


    /**
     * 插入
     * 新增数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer insert(Customer customer) {
        this.customerDao.insert(customer);
        return customer;
    }

    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 实例对象
     */
    @Override
    public Customer update(Customer customer) {
        this.customerDao.update(customer);
        return this.queryById(customer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.customerDao.deleteById(id) > 0;
    }

    @Override
    public Customer queryByOpenid(String openid) {
        return customerDao.queryByOpenid(openid);
    }

    @Override
    public Customer queryByRequestCode(String requestCode) {
        return customerDao.queryByRequestCode(requestCode);
    }

    @Override
    public Customer queryFirstParent(Integer firstParent) {
        return customerDao.queryFirstParent(firstParent);
    }
}
