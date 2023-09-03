package org.example.ydyd;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("org.example.ydyd.dao")
public class YdydMineApplication {
    public static void main(String[] args) {
        SpringApplication.run(YdydMineApplication.class, args);
    }

}
