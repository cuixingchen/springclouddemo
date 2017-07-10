package com.cuixingchen.springcloud.controller;

import com.cuixingchen.springcloud.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by cuipengfei on 17-7-10.
 */
public class RedisSessionControllerTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(RedisSessionControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    RedisSessionController redisSessionController;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //单个类  拦截器无效
        // mockMvc = MockMvcBuilders.standaloneSteup(userController).build();
    }

    @Test
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/session/add")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        logger.info("********************body:" + body);
    }
}
