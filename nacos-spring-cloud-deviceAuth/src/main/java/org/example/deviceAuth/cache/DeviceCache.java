package org.example.deviceAuth.cache;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class DeviceCache {
    private static Map<String,String> deviceMap = new ConcurrentHashMap<>();

    public static String getTokenById(String deviceId) {
        if (deviceMap.containsKey(deviceId)) {
            return deviceMap.get(deviceId);
        } else {
            return null;
        }
    }


    public static void add(String deviceId, String token) {

        deviceMap.put(deviceId, token);
    }


    public static void removeDevice(String deviceId) {

        deviceMap.remove(deviceId);
    }

}
