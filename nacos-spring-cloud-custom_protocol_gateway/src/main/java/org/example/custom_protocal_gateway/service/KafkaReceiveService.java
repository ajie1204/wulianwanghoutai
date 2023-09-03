package org.example.custom_protocal_gateway.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.internals.Topic;
import org.example.nacosspringcloudcommonentity.DownData;
import org.example.nacosspringcloudcommonentity.constant.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@Service
public class KafkaReceiveService {

    @Resource
    WaterUpClient waterUpClient;

    @KafkaListener(topics = Constants.Netty_uplink,groupId = "upData")
    public void upLinkReceive(ConsumerRecord<String,String> record){
        String system = record.key();
        String json = record.value();

        System.out.println(system);
        System.out.println(json);
        /*根据system字段，把json数据分配给对应的系统*/

        waterUpClient.upData(json);

    }


    @FeignClient(name = "waterPurifier",contextId = "up")
    public interface WaterUpClient{
        @PostMapping("/water/up")
        void upData(@RequestBody String json);
    }
}
