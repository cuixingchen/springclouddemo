package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by cuipengfei on 17-4-14.
 */
@SpringBootApplication
@EnableEurekaClient
public class BaseDataMongoApp {
    public static void main(String[] args) {
        SpringApplication.run(BaseDataMongoApp.class, args);
    }
}
