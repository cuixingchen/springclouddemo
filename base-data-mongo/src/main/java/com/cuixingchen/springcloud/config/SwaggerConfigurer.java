package com.cuixingchen.springcloud.config;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jersey.JerseyApiReader;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.reader.ClassReaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cuipengfei on 17-7-5.
 */
@Configuration
public class SwaggerConfigurer extends WebMvcConfigurerAdapter {

    public SwaggerConfigurer() {
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/**"}).addResourceLocations(new String[]{"classpath:/static/", "classpath:/templates/", "classpath:/META-INF/resources/webjars/"});
        super.addResourceHandlers(registry);
    }

    public static void initSwagger(String title, String description) {
        SwaggerConfig config = ConfigFactory.config();
        config.setBasePath("/v1");
        config.setApiVersion("1.0.0");
        config.setApiInfo(new ApiInfo(title, "<a href=\"/api\">" + description + "</a>", (String)null, (String)null, (String)null, (String)null));
        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        ClassReaders.setReader(new JerseyApiReader());
    }
}
