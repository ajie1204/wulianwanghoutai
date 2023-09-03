package org.example.nacosspringcloudauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//
@SpringBootApplication(scanBasePackages = {"org.example.nacosspringcloudauth","org.example.nacosspringcloudredis"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.example.nacosspringcloudauth.dao")
public class NacosSpringCloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudAuthApplication.class, args);
    }

}