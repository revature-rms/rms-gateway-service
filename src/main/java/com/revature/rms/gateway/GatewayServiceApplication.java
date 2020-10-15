package com.revature.rms.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

	@Value("${FARGATE_IP}")
	private static String fargateIp;

	public static void main(String[] args) {
		System.out.println(fargateIp);
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

}
