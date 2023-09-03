package org.example.custom_protocal_gateway;

import org.example.nacosspringcloudcommonentity.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
@Import(SpringUtil.class)
public class NettyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyServerApplication.class, args);
	}

}
