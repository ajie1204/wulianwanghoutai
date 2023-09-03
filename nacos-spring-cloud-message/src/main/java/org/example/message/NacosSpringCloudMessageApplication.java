package org.example.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.example.message.dao")
public class NacosSpringCloudMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudMessageApplication.class, args);
    }

}
