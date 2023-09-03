package org.example.scene;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.example.scene.dao")
public class NacosSpringCloudSceneApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudSceneApplication.class, args);
    }

}
