package com.lhuang.netty.im.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaClient//启用服务注册与发现
@EnableCircuitBreaker
@EnableFeignClients  //启用feign进行远程调用
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.lhuang.netty.im.common","com.lhuang.netty.im.client"})
public class ImClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImClientApplication.class, args);
    }

}
