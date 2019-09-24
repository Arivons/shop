package com.common.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommonRedisApp {
    public static void main(String [] args){
        SpringApplication.run(CommonRedisApp.class, args);
    }
}
