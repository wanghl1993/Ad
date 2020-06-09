package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@SpringCloudApplication
public class SearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    @LoadBalanced //开启负载均衡的能力
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

