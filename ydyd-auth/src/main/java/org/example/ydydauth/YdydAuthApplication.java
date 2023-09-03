package org.example.ydydauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 31477
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class YdydAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(YdydAuthApplication.class, args);
    }

}

