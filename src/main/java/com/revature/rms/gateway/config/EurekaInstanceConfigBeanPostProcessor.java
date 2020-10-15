package com.revature.rms.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EurekaInstanceConfigBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaInstanceConfigBeanPostProcessor.class);

    @Value("${FARGATE_IP}")
    private String fargateIp;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        LOGGER.info("EurekaInstanceConfigBeanPostProcessor - BeanPostProcessor.postProcessAfterInitialization invoked for bean with name: {}", beanName);

        if (bean instanceof EurekaInstanceConfigBean) {
            LOGGER.info("EurekaInstanceConfigBean detected. Setting IP address to {}", fargateIp);
            ((EurekaInstanceConfigBean) bean).setIpAddress(fargateIp);
        }

        return bean;
    }

}
