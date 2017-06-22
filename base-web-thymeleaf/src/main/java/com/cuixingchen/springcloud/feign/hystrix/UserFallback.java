package com.cuixingchen.springcloud.feign.hystrix;

import com.cuixingchen.springcloud.feign.UserFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-22.
 */
public class UserFallback implements UserFeign {

    private static Logger logger = LoggerFactory.getLogger(UserFallback.class);

    @Override
    public ResponseEntity<List<Map>> get(String userName) {
        logger.info("断路器get method");
        return null;
    }
}
