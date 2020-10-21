package com.revature.rms.gateway;

import com.revature.rms.core.config.EurekaInstanceConfigBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	@Bean
	public EurekaInstanceConfigBeanPostProcessor eurekaInstanceConfigBeanPostProcessor(){
		return new EurekaInstanceConfigBeanPostProcessor();
	}

}
