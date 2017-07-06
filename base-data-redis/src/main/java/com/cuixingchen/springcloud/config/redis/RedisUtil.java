package com.cuixingchen.springcloud.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by cuipengfei on 17-7-6.
 */
@Component
public class RedisUtil {

    Logger logger= LoggerFactory.getLogger(RedisUtil.class);


    public void test(){
        logger.info("RedisUtil test");
    }


}
