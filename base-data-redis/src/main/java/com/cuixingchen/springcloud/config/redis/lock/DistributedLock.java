package com.cuixingchen.springcloud.config.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.jedis.JedisConverters;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuipengfei on 17-7-9.
 */
public class DistributedLock {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    private static final String LOCK_PREFIX = "CPX:LOCK:";

    private static final long DEFAULT_SLEEP_TIME = 100;


    private RedisTemplate redisTemplate;
    private RedisSerializer<String> stringSerializer;
    /**
     * key值
     */
    private String lockKey;
    /**
     * value值，保证唯一
     */
    private String value = UUID.randomUUID().toString();
    /**
     * 有效期，单位：毫秒
     */
    private long expireTime = 20000;
    /**
     * 是否获得锁
     */
    private boolean locked = false;

    /**
     * 功能描述：构造函数
     *
     * @param redisTemplate
     * @param lockKey       key值
     * @param expireTime    有效期，单位：毫秒
     */
    public DistributedLock(RedisTemplate redisTemplate, String lockKey, long expireTime) {
        this.redisTemplate = redisTemplate;
        this.lockKey = LOCK_PREFIX + lockKey;
        this.expireTime = expireTime;
        this.stringSerializer = redisTemplate.getStringSerializer();
    }

    /**
     * 功能描述: 获取阻塞锁，如果锁空闲立即返回 ，获取失败 一直重试
     *
     * @param sleepTime 线程sleep时间，单位：毫秒
     * @throws InterruptedException
     * @Author gy
     * @Date 2016年8月14日下午9:12:40
     */
    public void lock(long sleepTime) throws InterruptedException {
        if (sleepTime <= 0) {
            sleepTime = DEFAULT_SLEEP_TIME;
        }
        do {
            boolean flag = setnxpx();
            if (flag) {
                locked = true;
                logger.info(Thread.currentThread().getName() + "(lock)获取到锁" + value);
                return;
            }
            Thread.sleep(sleepTime);
        } while (true);
    }

    /**
     * 功能描述: 获取立即返回锁，如果锁可用，立即返回true， 否则返回false
     *
     * @return 成功失败标志，true表示获取锁成功，false失败
     */
    public boolean tryLock() {
        return tryLock(0, 0);
    }

    /**
     * 功能描述：获取等待重试锁，锁在给定的超时时间内空闲，则获取锁成功 返回true， 否则返回false
     *
     * @param timeout   重试超时时间，单位：毫秒
     * @param sleepTime 线程sleep时间，仅timeout大于0时有效，单位：毫秒
     * @return 成功失败标志，true表示获取锁成功，false失败
     */
    public boolean tryLock(long timeout, long sleepTime) {
        long nano = System.nanoTime();
        try {
            do {
                boolean flag = setnxpx();
                if (flag) {
                    locked = true;
                    logger.info(Thread.currentThread().getName() + "(tryLock)获取到锁" + value);
                    return true;
                }
                if (timeout <= 0 || sleepTime <= 0) {
                    return false;
                }
                Thread.sleep(sleepTime);
            } while ((System.nanoTime() - nano) < TimeUnit.MILLISECONDS.toNanos(timeout));
        } catch (Exception e) {
            logger.error("DistributedLock tryLock error:" + e.getMessage(), e);
        }
        return false;
    }

    /**
     * 功能描述: 释放锁
     *
     * @Author gy
     * @Date 2016年8月14日下午7:51:20
     */
    public void unLock() {
        release();
    }

    private boolean setnxpx() {

        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                Expiration expiration = Expiration.milliseconds(expireTime);
                Object obj = jedis.set(stringSerializer.serialize(lockKey),
                        stringSerializer.serialize(value),
                        JedisConverters.toSetCommandNxXxArgument(RedisStringCommands.SetOption.ifAbsent()),
                        JedisConverters.toSetCommandExPxArgument(expiration), expiration.getExpirationTime()
                );
                connection.close();
                return obj;
            }
        });
        return result == null ? false : ("OK".equals(result.toString()) ? true : false);
    }

    private void release() {
        if (locked) {
            redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) {
                    // 获取redis中的值，验证是否与之前设置的值相等，如果相等，则删除，避免删除掉其他线程的锁
                    Object obj = stringSerializer.deserialize(connection.get(stringSerializer.serialize(lockKey)));
                    if (value.equals(obj != null ? obj.toString() : null)) {
                        logger.info(Thread.currentThread().getName() + "释放锁" + value);
                        connection.del(stringSerializer.serialize(lockKey));
                    }
                    connection.close();
                    return null;
                }
            });
            locked = false;
        }
    }
}
