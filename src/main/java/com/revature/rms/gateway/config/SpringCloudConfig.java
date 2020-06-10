package com.revature.rms.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Alternative to routing services in yml file.
 *
 * TODO: Include routes for ALL services.
 */

//@Configuration
public class SpringCloudConfig {

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/batch/**")
//                        .uri("lb://batch-service")
//                        .id("batchModule"))
//                .route(r -> r.path("/campus/**")
//                        .uri("lb://campus-service")
//                        .id("campusModule"))
//                .build();
//    }
}