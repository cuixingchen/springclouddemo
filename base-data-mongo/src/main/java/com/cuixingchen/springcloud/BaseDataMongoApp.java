package com.cuixingchen.springcloud;

import com.cuixingchen.springcloud.config.SwaggerConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by cuipengfei on 17-4-14.
 */
@SpringBootApplication
@EnableEurekaClient
public class BaseDataMongoApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(BaseDataMongoApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SwaggerConfigurer.initSwagger("base-data-mongo模块","base-data-mongo模块接口");
    }
}
