package com.cuixingchen.springcloud.config.redis.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * http://www.jb51.net/article/75439.htm
 * Created by cuipengfei on 17-7-11.
 */
public class RedisMQ {

    private static final Logger logger = LoggerFactory.getLogger(RedisMQ.class);

    private static final String QUEUE_PREFIX = "queue:";

    /**
     * 队列名称
     */
    private String redisQueue;

    private RedisTemplate<Object, Object> redisTemplate;

    public RedisMQ(String redisQueue, RedisTemplate redisTemplate) {
        this.redisQueue = QUEUE_PREFIX + redisQueue;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 入队（lpush）
     *
     * @param messege
     * @return
     */
    public long push(Object messege) {
        return redisTemplate.opsForList().leftPush(redisQueue, messege);
    }

    /**
     * 出队
     *
     * @param timeout
     * @param unit
     * @return
     */
    public Object pop(long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(redisQueue, timeout, unit);
    }
}
