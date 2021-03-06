package com.cuixingchen.springcloud.config.redis.mq;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by cuipengfei on 17-7-11.
 */
public class RedisMQObjTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(RedisMQ.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @After
    public void push() {
        RedisMQ redisMQ = new RedisMQ("cuiQueue", redisTemplate);
        for (int i = 0; i < 20; i++) {
            redisMQ.push(new Message("崔", i));
            redisMQ.push(new Message("鹏", i));
            redisMQ.push(new Message("飞", i));
        }
    }


    @Test
    public void test() {
        RedisMQ redisMQ = new RedisMQ("cuiQueue", redisTemplate);
        Object obj;
        while ((obj = redisMQ.pop(1000, TimeUnit.MILLISECONDS)) != null) {
            if (obj instanceof Message) {
                logger.info("队列元素：" + obj.toString());
            } else {
                logger.info("no");
            }
        }
    }
}
