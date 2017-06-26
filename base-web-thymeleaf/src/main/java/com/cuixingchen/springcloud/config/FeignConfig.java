package com.cuixingchen.springcloud.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cuipengfei on 17-6-26.
 */
@Configuration
public class FeignConfig {

    @Bean
    public Contract feignContract() {
        return new JAXRSContract();
    }
}
