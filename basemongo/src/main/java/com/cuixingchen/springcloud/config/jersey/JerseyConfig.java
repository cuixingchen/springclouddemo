package com.cuixingchen.springcloud.config.jersey;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

/**
 * Created by cuipengfei on 17-2-27.
 */
@Configuration
public class JerseyConfig extends ResourceConfig{

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

    public JerseyConfig() {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        register(provider);
        // 所有resource自动扫描，不需要再依次手动添加
        packages("com.cuixingchen.springcloud.resource");
    }
}
