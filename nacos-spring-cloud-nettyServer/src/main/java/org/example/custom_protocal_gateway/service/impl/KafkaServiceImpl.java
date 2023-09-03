package org.example.custom_protocal_gateway.service.impl;

import org.example.custom_protocal_gateway.service.KafkaService;
import org.example.nacosspringcloudcommonentity.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void upLink(String system,String msg) {
        System.out.println("调用uplink方法");
        kafkaTemplate.send(Constants.Netty_uplink,system,msg);

    }
}
