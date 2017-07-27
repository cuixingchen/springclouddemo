package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by cuipengfei on 17-6-28.
 */
@SpringBootApplication
//@EnableEurekaClient
public class BaseDataRedisApp {

    public static void main(String[] args) {
        SpringApplication.run(BaseDataRedisApp.class, args);
    }
}
