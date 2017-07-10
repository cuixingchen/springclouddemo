package com.cuixingchen.springcloud.config.redis;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.function.Consumer;

/**
 * Created by cuipengfei on 17-7-9.
 */
public class DistributedLockTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 锁正常使用
     * 阻塞等待获取锁执行任务
     */
    @Test
    public void testLock() {
        testCommon(redisTemplate, template -> {
            DistributedLock distributedLock = new DistributedLock(template, "cuipengfei", 10 * 1000);
            try {
                distributedLock.lock(0);
                logger.info(Thread.currentThread().getName() + "线程执行中。。。。。");
                Thread.sleep(3000);
                distributedLock.unLock();
            } catch (InterruptedException e) {
                logger.error(Thread.currentThread().getName() + "线程异常信息：", e);
            }
        });
    }

    /**
     * 尝试获取锁后立刻返回结果
     */
    @Test
    public void testTryLock() {

        testCommon(redisTemplate, template -> {
            DistributedLock distributedLock = new DistributedLock(template, "cuipengfei", 10 * 1000);
            try {
                if (distributedLock.tryLock()) {
                    logger.info(Thread.currentThread().getName() + "线程执行中。。。。。");
                    Thread.sleep(3000);
                    distributedLock.unLock();
                } else {
                    logger.info(Thread.currentThread().getName() + "线程任务未执行");
                }

            } catch (InterruptedException e) {
                logger.error(Thread.currentThread().getName() + "线程异常信息：", e);
            }
        });
    }

    /**
     * 任务时间大约锁过期时间
     * 正常：获取锁逻辑正常
     * 问题1：同时有多个任务同时执行的情况
     * 问题1：锁释放有问题，锁value一直是一样的，所以value判断没有启作用，出现误释放别人的锁
     * 解决方案：暂无
     */
    @Test
    public void testTryLock2() {

        testCommon(redisTemplate, template -> {
            DistributedLock distributedLock = new DistributedLock(template, "cuipengfei", 1 * 1000);
            try {
                if (distributedLock.tryLock(10 * 1000, 100)) {
                    logger.info(Thread.currentThread().getName() + "线程执行中。。。。。");
                    Thread.sleep(3000);
                    distributedLock.unLock();
                } else {
                    logger.info(Thread.currentThread().getName() + "线程任务未执行");
                }

            } catch (InterruptedException e) {
                logger.error(Thread.currentThread().getName() + "线程异常信息：", e);
            }
        });
    }

    private void testCommon(RedisTemplate<String, String> redisTemplate, Consumer<RedisTemplate<String, String>> function) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    function.accept(redisTemplate);
                } catch (Exception e) {
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
