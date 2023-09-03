package org.example.nacosspringclouddevicemanage.service.water_service.water_serviceImpl;

import org.example.nacosspringcloudcommonentity.util.TimeUtil;
import org.example.nacosspringcloudcommonentity.water.WaterFilter;
import org.example.nacosspringcloudcommonentity.water.WaterLife;
import org.example.nacosspringclouddevicemanage.service.water_service.WaterFilterService;
import org.example.nacosspringclouddevicemanage.service.water_service.WaterLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WaterLifeServiceImpl implements WaterLifeService {

    @Autowired
    private WaterFilterService waterFilterService;

    @Override
    public WaterLife countLife(Date time) {
        WaterLife waterLife = new WaterLife();
        Date date = new Date();
        int day365 = TimeUtil.getDays(365, time, date);
        int day180 = TimeUtil.getDays(180, time, date);
        waterLife.setRo_life(TimeUtil.getDays(730, time, date));
        waterLife.setRemainder(day365);
        waterLife.setT33_life(day365);
        waterLife.setPp_life(day180);
        waterLife.setGac_life(day180);
        return waterLife;
    }

    @Override
    public WaterLife count(WaterFilter waterFilter) {
        WaterLife waterLife = new WaterLife();
        waterLife.setRemainder(waterFilter.getRemainder());
        waterLife.setRo_life(waterFilter.getRO());
        waterLife.setT33_life(waterFilter.getT33());
        waterLife.setPp_life(waterFilter.getPP());
        waterLife.setGac_life(waterFilter.getGAC());
        return waterLife;
    }

}
