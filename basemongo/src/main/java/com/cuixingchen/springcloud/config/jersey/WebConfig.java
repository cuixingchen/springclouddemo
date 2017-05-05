package com.cuixingchen.springcloud.config.jersey;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

/**
 * Created by cuipengfei on 17-4-14.
 */
@Configuration
public class WebConfig implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {

    }

    /**
     * 解决中文内容编码问题，统一用UTF-8编码
     *
     * @return
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;
    }
}
