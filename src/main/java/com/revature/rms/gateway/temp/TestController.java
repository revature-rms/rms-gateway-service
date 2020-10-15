package com.revature.rms.gateway.temp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${FARGATE_IP}")
    private String fargateIp;

    @GetMapping
    public String getFargateIp() {
        return fargateIp;
    }

}
