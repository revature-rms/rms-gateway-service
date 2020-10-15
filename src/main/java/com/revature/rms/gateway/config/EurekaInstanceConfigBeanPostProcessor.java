package com.revature.rms.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@Component
@Profile("dev")
public class EurekaInstanceConfigBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaInstanceConfigBeanPostProcessor.class);

    private String fargateIp;

    {
        try {
            fargateIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.warn("Could not get the Fargate instance ip address.");
        }
    }

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
