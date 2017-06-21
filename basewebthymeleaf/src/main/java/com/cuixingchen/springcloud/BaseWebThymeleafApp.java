package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by cuipengfei on 17-6-15.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BaseWebThymeleafApp {
    public static void main(String[] args) {
        SpringApplication.run(BaseWebThymeleafApp.class, args);
    }
}
