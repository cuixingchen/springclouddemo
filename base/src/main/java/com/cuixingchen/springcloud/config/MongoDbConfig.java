package com.cuixingchen.springcloud.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 *
 * 
 *  通用配置类
 */
@Configuration//等价beans注解|@Bean等价于XML中配置的bean
public class MongoDbConfig implements EnvironmentAware {

	// 解析application.yml
	private RelaxedPropertyResolver propResolver;

	//MongoDB相关
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		ServerAddress serverAddress = new ServerAddress(propResolver.getProperty("spring.data.mongodb.host"),
				Integer.parseInt(propResolver.getProperty("spring.data.mongodb.port")));
		MongoClientOptions.Builder builder = MongoClientOptions.builder().socketKeepAlive(true)       // 是否保持长链接
				.connectTimeout(5000)   // 链接超时时间
				.socketTimeout(50000)    // read数据超时时间
				.connectionsPerHost(30) // 每个地址最大请求数
				.threadsAllowedToBlockForConnectionMultiplier(50) // 一个socket最大的等待请求数
				.writeConcern(WriteConcern.UNACKNOWLEDGED);

		return new SimpleMongoDbFactory(
				new MongoClient(serverAddress, builder.build()),
				propResolver.getProperty("spring.data.mongodb.dbname"));
	}

	//MongoDB相关
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		propResolver = new RelaxedPropertyResolver(environment);
	}

}
