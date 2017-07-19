package com.cuixingchen.springcloud.service;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * http://blog.csdn.net/it_man/article/details/5074371
 * Created by cuipengfei on 17-7-18.
 */
public class TransactionlTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TransactionlTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void requiredTest() {
        userService.transactional_required_helper(1L, "支持当前事务，如没有就新建");
    }
}
