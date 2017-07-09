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

    Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void test() {
        redisTemplate.opsForValue().set("cui", "123");
        redisTemplate.opsForValue().set("peng", "鹏");
        redisTemplate.opsForValue().set("fei", "飞");
        stringRedisTemplate.opsForValue().set("state","success");
        stringRedisTemplate.opsForValue().getAndSet("state","error");
        stringRedisTemplate.opsForValue().setIfAbsent("state","over");
        logger.info("RedisUtil test");
    }

    public void get() {

        StringBuilder result = new StringBuilder();
        result.append(redisTemplate.opsForValue().get("cui"));
        result.append(stringRedisTemplate.opsForValue().get("peng"));
        result.append(stringRedisTemplate.opsForValue().get("fei"));
        result.append(stringRedisTemplate.opsForValue().get("state"));
        logger.info("redis--get：" + result);
    }


}
