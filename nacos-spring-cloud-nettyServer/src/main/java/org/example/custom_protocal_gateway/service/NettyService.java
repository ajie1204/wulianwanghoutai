package org.example.custom_protocal_gateway.service;

public interface NettyService {

    public void sendTCPCommand(String deviceID,String jsonCommand);

    public void sendMQTTCommand(String msgKey,String jsonObj);
}
