package com.cuixingchen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by cuipengfei on 17-6-15.
 */
@SpringBootApplication
public class BaseWebFreeMarkerApp {
    public static void main(String[] args) {
        SpringApplication.run(BackgroundPreinitializer.class, args);
    }
}
