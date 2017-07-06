package com.cuixingchen.springcloud.config.redis;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cuipengfei on 17-7-6.
 */
public class RedisTest extends BaseTest{

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test(){
        redisUtil.test();
    }
}
