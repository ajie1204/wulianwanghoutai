package org.example.nacosspringcloudinteract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;


@SpringBootApplication(scanBasePackages = {"org.example.nacosspringcloudinteract","org.example.nacosspringcloudredis"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableOpenApi
public class NacosSpringCloudInteractApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringCloudInteractApplication.class, args);
    }

}
