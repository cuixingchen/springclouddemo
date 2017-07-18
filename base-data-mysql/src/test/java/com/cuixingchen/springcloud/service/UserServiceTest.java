package com.cuixingchen.springcloud.service;

import com.cuixingchen.springcloud.BaseTest;
import com.cuixingchen.springcloud.api.user.UserPojo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cuipengfei on 17-7-18.
 */
public class UserServiceTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void addTest() {
        UserPojo userPojo = new UserPojo();
        userPojo.setUserId("userId1");
        userPojo.setUserName("cuixingchen");
        userPojo.setDescript("测试账户");
        userService.add(userPojo);
    }

    @Test
    public void getListTest() {
        List<UserPojo> list = userService.getList("cui");
        logger.info(list.toString());
    }

    @Test
    public void updateTest() {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(1L);
        userPojo.setDescript("测试账户update1");
        int count = userService.updateUser(userPojo);
        logger.info("更新成功条数：" + count);
    }
}
