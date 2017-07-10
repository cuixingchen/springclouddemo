package com.cuixingchen.springcloud.config.redis.lock;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.function.Supplier;

/**
 * Created by cuipengfei on 17-7-9.
 */
public class RedisLockTest extends BaseTest {

    private static Logger logger = LoggerFactory.getLogger(RedisLockTest.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 任务时间小于锁过期时间
     * 正常：锁获取释放有序
     */
    @Test
    public void test() {
        testCommon(() -> new RedisLock(redisTemplate, "cuipengfei"));
    }

    /**
     * 任务时间大约锁过期时间
     * 正常：锁获取逻辑没有问题
     * 问题1：同时有多个任务同时执行的情况
     * 问题2：锁释放混乱,释放的不是自己的锁
     */
    @Test
    public void testExpire() {
        testCommon(() -> new RedisLock(redisTemplate, "cuipengfei", 10 * 1000, 1 * 1000));
    }

    private void testCommon(Supplier<RedisLock> supplier) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    RedisLock redisLock = supplier.get();
                    if (redisLock.lock()) {
                        logger.info(Thread.currentThread().getName() + "线程执行中。。。。。");
                        Thread.sleep(3000);
                        redisLock.unlock();
                    } else {
                        logger.info(Thread.currentThread().getName() + "任务未执行");
                    }
                } catch (InterruptedException e) {
                    logger.error(Thread.currentThread().getName() + "线程异常信息：", e);
                }

            }, "thread-i=" + i).start();
        }
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName(), e);
        }
    }
}
