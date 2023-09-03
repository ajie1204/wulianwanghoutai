package org.example.ydyd.dao;

import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.nacosspringcloudcommonentity.customer.CustomerDetail;
import org.example.nacosspringcloudcommonentity.customer.UserLevel;
import org.springframework.stereotype.Repository;


/**
 * 用户表(Customer)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-03 17:48:28
 */
@Repository
public interface CustomerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Customer queryById(Integer id);


    /**
     * 通过手机号查询
     *
     * @param mobile 移动
     * @return {@link Customer}
     */
    Customer queryByMobile(String mobile);


    /**
     * 通过手机号查询等级
     *
     * @param mobile 移动
     * @return {@link UserLevel}
     */
    UserLevel queryCustomerLevelByMobile(String mobile);

    /**
     * 通过手机号查询用户细节
     *
     * @param mobile 移动
     * @return {@link UserLevel}
     */
    CustomerDetail queryCustomerDetailByMobile(String mobile);



    /**
     * 新增数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int insert(Customer customer);



    /**
     * 修改数据
     *
     * @param customer 实例对象
     * @return 影响行数
     */
    int update(Customer customer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 通过openid查询
     *
     * @param openid openid
     * @return {@link Customer}
     */
    Customer queryByOpenid(String openid);

    /**
     * 通过邀请码查询用户
     *
     * @param requestCode 请求代码
     * @return {@link Customer}
     */
    Customer queryByRequestCode(String requestCode);

    /**
     * 查询父级
     *
     * @param firstParent 第一个父
     * @return {@link Customer}
     */
    Customer queryFirstParent(Integer firstParent);

}

