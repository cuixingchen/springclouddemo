package com.cuixingchen.springcloud.config.jersey;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by cuipengfei on 17-4-14.
 */
@Configuration
public class WebConfig implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {

    }
}
