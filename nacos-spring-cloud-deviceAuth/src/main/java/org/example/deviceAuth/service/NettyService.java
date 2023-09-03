package org.example.deviceAuth.service;

public interface NettyService {

    public void sendTCPCommand(
            String deviceID,String jsonCommand);
}
