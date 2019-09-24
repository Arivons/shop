package com.frontend.order;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("com.mapper")
@EnableFeignClients
public class FrontendOrderApp {
    public static void main(String[] args){
        SpringApplication.run(FrontendOrderApp.class, args);
    }
    @Bean
    public AvailabilityFilteringRule ribbonLoadBalancer(){
        return  new AvailabilityFilteringRule();
    }
}
