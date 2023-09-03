package org.example.nacosspringcloudinteract.service;

import io.swagger.models.auth.In;
import org.example.nacosspringcloudcommonentity.Place;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@FeignClient("placeManage")
public interface PlaceService {

    @PostMapping("/place/select")
    List<Place> selectPlace(Integer placeId);

    @GetMapping("/place/getAll/{userId}")
    List<Place> getAll(@PathVariable Integer userId);


    @PostMapping("/place/insert")
    int insertPlace(Place place);


    @PostMapping("/place/delete")
    int delete(Integer placeId);


}
