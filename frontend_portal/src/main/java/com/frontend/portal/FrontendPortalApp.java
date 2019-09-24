package com.frontend.portal;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FrontendPortalApp {
    public static void main(String [] args){
        SpringApplication.run(FrontendPortalApp.class, args);
    }
    @Bean
    public AvailabilityFilteringRule ribbonLoadBalancer(){
        return  new AvailabilityFilteringRule();
    }
}
