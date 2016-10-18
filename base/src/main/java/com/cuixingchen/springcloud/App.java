package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by cuipengfei on 16-10-18.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
