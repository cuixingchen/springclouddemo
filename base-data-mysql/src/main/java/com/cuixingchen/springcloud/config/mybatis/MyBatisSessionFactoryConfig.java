/**    
 * @Title: MybatisConfiguration.java  
 * @Package com.jyall.service.centerhouse.common  
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author zhao.liang    
 * @date 2016年3月12日 下午1:34:13  
 * @version V1.0    
 */
package com.cuixingchen.springcloud.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 
 * 获取第二个数据库的连接信息，在application.yml中配置，并指定特定的前缀
 * 
 */
@Configuration
@AutoConfigureAfter({MyBatisDataSourceAutoConfig.class})
@EnableTransactionManagement
public class MyBatisSessionFactoryConfig implements EnvironmentAware,TransactionManagementConfigurer {
	private static Logger logger = LoggerFactory.getLogger(MyBatisSessionFactoryConfig.class);

	private RelaxedPropertyResolver propertyResolver;

	@Resource(name = "writeDataSource")
	private DataSource writeDataSource;

	@Resource(name = "readDataSource")
	private DataSource readDataSource;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
	}

	@Bean(name="writeSqlSessionFactory")
	@Primary
	public SqlSessionFactory writeSqlSessionFactory() {
		try {
			SqlSessionFactoryBean writeSqlSessionFactory = new SqlSessionFactoryBean();
			writeSqlSessionFactory.setDataSource(writeDataSource);
			writeSqlSessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(propertyResolver.getProperty("config")));
			writeSqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("writeMapperLocations")));

			return writeSqlSessionFactory.getObject();
		} catch (Exception e) {
			logger.error("Could not confiure mybatis session factory",e);
			return null;
		}
	}

	@Bean(name="readSqlSessionFactory")
	public SqlSessionFactory readSqlSessionFactory() {
		try {
			SqlSessionFactoryBean readSqlSessionFactory = new SqlSessionFactoryBean();
			readSqlSessionFactory.setDataSource(readDataSource);
			readSqlSessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(propertyResolver.getProperty("config")));
			readSqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(propertyResolver.getProperty("readMapperLocations")));

			return readSqlSessionFactory.getObject();
		} catch (Exception e) {
			logger.error("Could not confiure mybatis session factory",e);
			return null;
		}
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		logger.info("数据库事务开启~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return new DataSourceTransactionManager(writeDataSource);
	}
}
