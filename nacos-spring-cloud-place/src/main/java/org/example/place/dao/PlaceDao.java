package org.example.place.dao;

import org.apache.ibatis.annotations.Param;
import org.example.nacosspringcloudcommonentity.Place;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Place)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-07 19:02:07
 */
@Repository
public interface PlaceDao {


    /**
     * 通过userId查询placeName
     *
     * @param userId 用户名
     * @return 实例对象
     */
    List<Place> selectPlaceName(Integer userId);


    /**
     * 通过ID查询单条数据
     *
     * @param placeId 主键
     * @return 实例对象
     */
    Place selectById(Integer placeId);


    /**
     * 按名称查询
     *
     * @return 对象列表
     */
    List<Place> selectByName(String placeName);
	
    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Place> selectPage(@Param("start") int start, @Param("limit") int limit);

    /**
     * 查询全部
     *
     * @return 对象列表
     */
    List<Place> selectAll(int userId);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param place 实例对象
     * @return 对象列表
     */
    List<Place> selectList(Place place);

    /**
     * 新增数据
     *
     * @param place 实例对象
     * @return 影响行数
     */
    int insert(Place place);
	
	/**
     * 批量新增
     *
     * @param places 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Place> places);
	
    /**
     * 修改数据
     *
     * @param place 实例对象
     * @return 影响行数
     */
    int update(Place place);

    /**
     * 通过主键删除数据
     *
     * @param placeId 主键
     * @return 影响行数
     */
    int deleteById(Integer placeId);

    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    int count();
}

