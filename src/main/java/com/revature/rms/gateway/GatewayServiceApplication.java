package com.revature.rms.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//	import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
<<<<<<< HEAD
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

//@EnableSwagger2
=======
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
>>>>>>> b575d24ee4991a3bbd07b9358631758cfb449d98
@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

//	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

<<<<<<< HEAD
//	@Autowired
//	RouteDefinitionLocator locator;
//
//	@Bean
//	public List<GroupedOpenApi> apis() {
//		List<GroupedOpenApi> groups = new ArrayList<>();
//		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
//			String name = routeDefinition.getId().replaceAll("-service", "");
//			GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build();
//		});
//		return groups;
//	}

=======
//	@Bean
//	UiConfiguration uiConfig() {
//		return new UiConfiguration("validatorUrl", "list", "alpha", "stream",
//				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
//	}
>>>>>>> b575d24ee4991a3bbd07b9358631758cfb449d98
}
