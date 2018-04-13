package com.netas.devices_messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class DataManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataManagerServiceApplication.class, args);
	}
}
