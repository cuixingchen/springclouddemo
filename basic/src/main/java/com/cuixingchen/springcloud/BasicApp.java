package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by cuipengfei on 17-6-13.
 */
@SpringBootApplication
@EnableEurekaClient
public class BasicApp {
    public static void main(String[] args) {
        SpringApplication.run(BasicApp.class, args);
    }
}
