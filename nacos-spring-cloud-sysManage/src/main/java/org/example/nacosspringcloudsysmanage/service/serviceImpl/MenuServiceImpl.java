package org.example.nacosspringcloudsysmanage.service.serviceImpl;

import org.example.nacosspringcloudcommonentity.Menu;
import org.example.nacosspringcloudsysmanage.dao.MenuDao;
import org.example.nacosspringcloudsysmanage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int add(Menu menu) {
        return menuDao.add(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public List<Menu> findByUser(int userId) {
        return menuDao.findByUser(userId);
    }

    @Override
    public List<Menu> findByRole(int roleId) {

        return menuDao.findByRole(roleId);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuDao.updateMenu(menu);
    }

    @Override
    public void delMenu(Menu menu) {
        menuDao.delMenu(menu);
    }
}
