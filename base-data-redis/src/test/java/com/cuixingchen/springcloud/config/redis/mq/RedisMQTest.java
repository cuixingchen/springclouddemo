package com.cuixingchen.springcloud.config.redis.mq;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuipengfei on 17-7-11.
 */
public class RedisMQTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisMQ.class);

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @After
    public void push() {
        RedisMQ<String> redisMQ = new RedisMQ<>("cuiQueue", redisTemplate);
        for (int i = 0; i < 20; i++) {
            redisMQ.push(i + "崔");
            redisMQ.push(i + "鹏");
            redisMQ.push(i + "飞");
        }
        List<String> list = new ArrayList<>();
        list.add("崔鹏飞1");
        list.add("崔鹏飞2");
        list.add("崔鹏飞3");
        String[] ss = list.toArray(new String[list.size()]);
        redisMQ.pushAll(ss);
    }


    @Test
    public void test() {
        RedisMQ<String> redisMQ = new RedisMQ<>("cuiQueue", redisTemplate);
        Object str;
        while ((str = redisMQ.pop(1000, TimeUnit.MILLISECONDS)) != null) {
            logger.info("队列元素类型:"+str.getClass().getName());
            logger.info("队列元素：" + str.toString());
        }
    }
}
