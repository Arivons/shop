package com.frontend.sso;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.mapper")
public class FrontendSSOApp {
    public static void main(String[] args){
        SpringApplication.run(FrontendSSOApp.class, args);
    }
    @Bean
    public AvailabilityFilteringRule ribbonLoadBalancer(){
        return  new AvailabilityFilteringRule();
    }
}
