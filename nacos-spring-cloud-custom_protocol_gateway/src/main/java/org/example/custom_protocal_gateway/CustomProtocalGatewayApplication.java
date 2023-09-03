package org.example.custom_protocal_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CustomProtocalGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomProtocalGatewayApplication.class, args);
	}

}