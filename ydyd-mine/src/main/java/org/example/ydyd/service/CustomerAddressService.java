package org.example.ydyd.service;

import org.example.nacosspringcloudcommonentity.customer.CustomerAddress;

import java.util.List;

/**
 * 客户地址服务
 *
 * @author 31477
 * @date 2023/04/06
 */
public interface CustomerAddressService {

    /**
     * 查询客户地址unid
     *
     * @param unid unid
     * @return {@link List}<{@link CustomerAddress}>
     */
    List<CustomerAddress> queryCustomerAddressByUnid(Integer unid);

    /**
     * 插入客户地址
     *
     * @param customerAddress 客户地址
     * @return int
     */
    int insertCustomerAddress(CustomerAddress customerAddress);

    /**
     * 更新客户地址通过id
     *
     * @param customerAddress 客户地址
     * @return int
     */
    int updateCustomerAddressById(CustomerAddress customerAddress);

    /**
     * 删除客户地址通过id
     *
     * @param customerAddress 客户地址
     * @return int
     */
    int deleteCustomerAddressById(CustomerAddress customerAddress);

    /**
     * 更新def通过id
     *
     * @param customerAddress 客户地址
     * @return int
     */
    int updateDefById(CustomerAddress customerAddress);

}
