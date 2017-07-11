package com.cuixingchen.springcloud.config.redis.session;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by cuipengfei on 17-7-10.
 */
//这个类用配置redis服务器的连接
//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30, redisNamespace = "base-data-redis")
public class RedisSessionConfig {

    /**
     * http://blog.csdn.net/amayadream/article/details/50822018
     * 看过滤器的实现
     * 第一步getRequestedSessionId从头获取headerName
     * 第二步判断如果有就取当前的，如果没有就新创建一个sessionId
     * 第三步根据sessionId取redis中的session
     *
     * @return
     */
//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
//        headerHttpSessionStrategy.setHeaderName("base-data-redis-auth-token");
//        return headerHttpSessionStrategy;
//    }
}
