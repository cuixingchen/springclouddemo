package com.cuixingchen.springcloud.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by cuipengfei on 17-7-6.
 */
@Component
public class RedisUtil {

    Logger logger= LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void test(){
        redisTemplate.opsForValue().set("key","123");
        logger.info("RedisUtil test");
    }

    public void get(){

        String result = stringRedisTemplate.opsForValue().get("key");
        logger.info("redis--get："+result);
    }



}
