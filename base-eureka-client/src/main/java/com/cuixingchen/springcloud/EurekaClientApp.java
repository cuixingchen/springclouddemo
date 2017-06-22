package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by cuipengfei on 17-5-15.
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApp {
    public static void main(String[] args){
        SpringApplication.run(EurekaClientApp.class, args);
    }
}
