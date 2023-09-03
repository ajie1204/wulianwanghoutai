package org.example.place.controller;

import org.example.nacosspringcloudcommonentity.Place;
import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.place.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * (Place)控制层
 *
 * @author makejava
 * @since 2022-04-07 19:02:06
 */
@RestController
@RequestMapping("/place")
public class PlaceController {
    /**
     * 服务对象
     */
    @Resource
    private PlaceService placeService;

    /**
     * 通过主键查询单条数据
     *
     * @param place 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response<Place> selectOne(Place place) {
        Place result = placeService.selectById(place.getPlaceId());
        if(result != null){
            Response.createSuccessResponse("查询成功", result);
        }
        return Response.createErrorResponse("查询失败");
    }

    /**
     * 根据名称查
     * @param placeName
     * @return
     */

    @PostMapping("/selectByName")
    public List<Place> selectByName(@RequestBody String placeName) {

        System.out.println(placeService.selectByName(placeName));
        return placeService.selectByName(placeName);
    }

    
    /**
     * 新增场所
     *
     * @return Response对象
     */
    @PostMapping("/insert")
    public int insert(@RequestBody Place place) throws Exception {

        // 判断场所名是否相同
        List<Place> places = placeService.selectPlaceName(place.getUserId());
        if (places != null && !places.isEmpty()) {
            for (Place existingPlace : places) {
                String existingPlaceName = existingPlace.getPlaceName();
                if (existingPlaceName.equals(place.getPlaceName())) {
                    throw new Exception("场所名重复");
                }
            }
        }

        int result = placeService.insert(place);
        return result;
    }

    @PostMapping("/select")
    public List<Place> select(@RequestBody Integer userId){

        return placeService.selectPlaceName(userId);
    }

    /**
     * 修改一条数据
     *
     * @param place 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Response<Place> update(@RequestBody Place place) {
        Place result = placeService.update(place);
        if (result != null) {
            Response.createSuccessResponse("修改成功", result);
        }
        return Response.createErrorResponse("修改失败");
    }

    /**
     * 删除一条数据
     *
     *
     * @return Response对象
     */
    @PostMapping(value = "delete")
    public int delete(@RequestBody Integer placeId) {
        System.out.println("删除场所方法");
        int result = placeService.deleteById(placeId);
        return result;
    }

    /**
     * 加载场所
     *
     * @return Response对象
     */
    @GetMapping("/getAll/{userId}")
    public List<Place> getAll(@PathVariable Integer userId) {
        List<Place> places = placeService.selectAll(userId);
        return places;
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.GET)
    public Response<Place> selectPage(Integer start, Integer limit) {
        List<Place> places = placeService.selectPage(start, limit);
        if (places != null) {
            Response.createSuccessResponse("查询成功", places);
        }
        return Response.createErrorResponse("查询失败");
    }





}

