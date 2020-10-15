package com.revature.rms.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ContainerEnvironmentAware implements EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerEnvironmentAware.class);

    @Override
    public void setEnvironment(Environment environment) {
        Properties props = new Properties();
        try {
            LOGGER.info("setting FARGATE_IP address to {}", InetAddress.getLocalHost().getHostAddress());
            props.setProperty("FARGATE_IP", InetAddress.getLocalHost().getHostAddress());
            PropertiesPropertySource propertySource = new PropertiesPropertySource("ECS", props);
            if (environment instanceof StandardEnvironment) {
                ((StandardEnvironment) environment).getPropertySources().addFirst(propertySource);
            }
        } catch (UnknownHostException e) {
            LOGGER.warn("Could not get the Fargate instance ip address.");
        }
    }

}
