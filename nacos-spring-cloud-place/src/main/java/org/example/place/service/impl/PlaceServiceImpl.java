package org.example.place.service.impl;

import org.example.nacosspringcloudcommonentity.Place;
import org.example.place.dao.PlaceDao;
import org.example.place.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Place表)服务实现类
 *
 * @author makejava
 * @since 2022-04-07 19:02:07
 */
@Service("placeService")
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDao placeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 用户id
     * @return 实例对象
     */
    @Override
    public List<Place> selectPlaceName(Integer userId) {
        return this.placeDao.selectPlaceName(userId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param placeId 主键
     * @return 实例对象
     */
    @Override
    public Place selectById(Integer placeId) {

        return this.placeDao.selectById(placeId);
    }

    @Override
    public List<Place> selectByName(String placeName) {
        return placeDao.selectByName(placeName);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Place> selectPage(int start, int limit) {
        return this.placeDao.selectPage(start, limit);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     @Override
     public List<Place> selectAll(int userID) {
        return this.placeDao.selectAll(userID);
     }
     
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Place> selectList(Place place) {
        return this.placeDao.selectList(place);
    }
    
    /**
     * 新增数据
     *
     * @param place 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Place place) {

        return this.placeDao.insert(place);
    }

    /**
     * 批量新增
     *
     * @param places 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Place> places) {
        return this.placeDao.batchInsert(places);
    }

    /**
     * 修改数据
     *
     * @param place 实例对象
     * @return 实例对象
     */
    @Override
    public Place update(Place place) {
        this.placeDao.update(place);
        return this.selectById(place.getPlaceId());
    }

    /**
     * 通过主键删除数据
     *
     * @param placeId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer placeId) {
        return this.placeDao.deleteById(placeId);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
     @Override
     public int count(){
        return this.placeDao.count();
     }
}

