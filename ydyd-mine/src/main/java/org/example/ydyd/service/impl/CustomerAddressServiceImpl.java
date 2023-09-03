package org.example.ydyd.service.impl;

import org.example.nacosspringcloudcommonentity.customer.CustomerAddress;
import org.example.ydyd.dao.CustomerAddressDao;
import org.example.ydyd.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户地址服务impl
 *
 * @author 31477
 * @date 2023/04/07
 */
@Service("customerAddressService")
public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Autowired
    CustomerAddressDao customerAddressDao;
    /**
     * 查询客户地址unid
     *
     * @param unid unid
     * @return {@link List}<{@link CustomerAddress}>
     */
    @Override
    public List<CustomerAddress> queryCustomerAddressByUnid(Integer unid) {
        return customerAddressDao.queryCustomerAddressByUnid(unid);
    }

    /**
     * 插入客户地址unid
     *
     * @param customerAddress 客户地址
     * @return int
     */
    @Override
    public int insertCustomerAddress(CustomerAddress customerAddress) {
        return customerAddressDao.insertCustomerAddress(customerAddress);
    }

    /**
     * 更新客户地址通过id
     *
     * @param customerAddress 客户地址
     * @return int
     */
    @Override
    public int updateCustomerAddressById(CustomerAddress customerAddress) {
        return customerAddressDao.updateCustomerAddressById(customerAddress);
    }

    /**
     * 删除客户地址通过id
     *
     * @param customerAddress 客户地址
     * @return int
     */
    @Override
    public int deleteCustomerAddressById(CustomerAddress customerAddress) {
        return customerAddressDao.deleteCustomerAddressById(customerAddress);
    }

    @Override
    public int updateDefById(CustomerAddress customerAddress) {
        return customerAddressDao.updateDefById(customerAddress);
    }
}
