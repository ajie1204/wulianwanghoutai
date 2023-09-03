package org.example.ydyd.service.impl;

import org.example.nacosspringcloudcommonentity.customer.Customer;
import org.example.ydyd.dao.VipDao;
import org.example.ydyd.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 31477
 */
@Service("vipservice")
public class VipServiceImpl implements VipService {
    @Autowired
    private VipDao vipDao;

    @Override
    public int registeredVip(Integer id) {
        return vipDao.registeredVip(id);
    }
}
