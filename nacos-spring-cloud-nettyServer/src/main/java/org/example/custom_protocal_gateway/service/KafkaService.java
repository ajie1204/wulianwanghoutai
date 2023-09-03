package org.example.custom_protocal_gateway.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.custom_protocal_gateway.util.LogUtil;

public interface KafkaService {


    public void upLink(String system,String msg);

}
