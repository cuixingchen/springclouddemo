package com.cuixingchen.springcloud.config.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by cuipengfei on 17-6-28.
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class MyBatisDataSourceAutoConfig {
    private static Logger log = LoggerFactory.getLogger(MyBatisDataSourceAutoConfig.class);

    private final MybatisProperties mybatisProperties;

    public MyBatisDataSourceAutoConfig(MybatisProperties mybatisProperties) {
        this.mybatisProperties = mybatisProperties;
    }

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        log.debug("Configruing Write DataSource");

        HikariConfig datasource = new HikariConfig();
        datasource.setDriverClassName(mybatisProperties.getDriverClassName());
        datasource.setJdbcUrl(mybatisProperties.getWriteConfig().getUrl());
        datasource.setUsername(mybatisProperties.getWriteConfig().getUsername());
        datasource.setPassword(mybatisProperties.getWriteConfig().getPassword());
        datasource.setAutoCommit(false);
        return new HikariDataSource(datasource);
    }

    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        log.debug("Configruing Read DataSource");
        HikariConfig datasource = new HikariConfig();
        datasource.setDriverClassName(mybatisProperties.getDriverClassName());
        datasource.setJdbcUrl(mybatisProperties.getReadConfig().getUrl());
        datasource.setUsername(mybatisProperties.getReadConfig().getUsername());
        datasource.setPassword(mybatisProperties.getReadConfig().getPassword());

        return new HikariDataSource(datasource);
    }
}
