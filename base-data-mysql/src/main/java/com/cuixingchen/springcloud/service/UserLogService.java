package com.cuixingchen.springcloud.service;

import com.cuixingchen.springcloud.api.user.UserLog;
import com.cuixingchen.springcloud.mapper.write.UserLogWriteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by cuipengfei on 17-7-20.
 */
@Service
public class UserLogService {

    private static final Logger logger = LoggerFactory.getLogger(UserLogService.class);

    @Autowired
    private UserLogWriteMapper userLogWriteMapper;

    /**
     * http://blog.csdn.net/it_man/article/details/5074371
     * 默认事务隔离级别
     * 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择
     *
     * @param userId
     * @param text
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addUserLog_required(Long userId, String text) {
        UserLog userLog = new UserLog(text, userId);
        userLog.setCreateTime(new Date());
        userLog.setUpdateTime(userLog.getCreateTime());
        userLogWriteMapper.add(userLog);
    }
}
