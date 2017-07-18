package com.cuixingchen.springcloud.service;

import com.cuixingchen.springcloud.BaseTest;
import com.cuixingchen.springcloud.api.user.UserPojo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cuipengfei on 17-7-18.
 */
public class TransactionlTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TransactionlTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void not_supportedTest() {
        UserPojo user = userService.getUserById(1L);
        logger.info("更新前用户信息：" + user.toString());
        userService.transational_not_supported(1L, "不支持事务");
        UserPojo userNow = userService.getUserById(1L);
        logger.info("更新后的用户信息：" + userNow.toString());
    }

    @Test
    public void requiredTest() {
        UserPojo user = userService.getUserById(1L);
        logger.info("更新前用户信息：" + user.toString());
        userService.transactional_required_helper(1L, "支持当前事务，如没有就新建","ex");
        UserPojo userNow = userService.getUserById(1L);
        logger.info("更新后的用户信息：" + userNow.toString());
    }
}
