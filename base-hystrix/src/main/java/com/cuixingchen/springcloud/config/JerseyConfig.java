package com.cuixingchen.springcloud.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cuipengfei on 17-2-27.
 */
@Configuration
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        register(provider);
        // 注册Resource，约定为上级目录resource下
        String packageName = JerseyConfig.class.getPackage().getName();
        // 所有resource自动扫描，不需要再依次手动添加
        packages("com.cuixingchen.springcloud.resource");
    }
}
