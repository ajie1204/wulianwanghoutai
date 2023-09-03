package org.example.ydyd.dao;

import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author 31477
 */
@Repository
public interface VipDao{

    /**
     * 注册会员
     *
     * @param id id
     * @return int
     */
    int registeredVip(Integer id);
}
