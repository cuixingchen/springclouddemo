package com.cuixingchen.springcloud.config.redis.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * http://www.jb51.net/article/75439.htm
 * Created by cuipengfei on 17-7-11.
 */
public class RedisMQ<T> {

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
     * @param t
     * @return
     */
    public long push(T t) {
        return redisTemplate.opsForList().leftPush(redisQueue, t);
    }

    /**
     * 批量入队
     * @param a
     * @return
     */
    public long pushAll(T[] a) {
        return redisTemplate.opsForList().leftPushAll(redisQueue, a);
    }

    /**
     * 出队
     *
     * @param timeout
     * @param unit
     * @return
     */
    public T pop(long timeout, TimeUnit unit) {
        return (T) redisTemplate.opsForList().rightPop(redisQueue, timeout, unit);
    }
}
