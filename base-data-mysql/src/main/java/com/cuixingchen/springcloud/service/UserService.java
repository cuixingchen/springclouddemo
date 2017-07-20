package com.cuixingchen.springcloud.service;

import com.cuixingchen.springcloud.api.user.UserLog;
import com.cuixingchen.springcloud.api.user.UserPojo;
import com.cuixingchen.springcloud.mapper.read.UserReadMapper;
import com.cuixingchen.springcloud.mapper.write.UserLogWriteMapper;
import com.cuixingchen.springcloud.mapper.write.UserWriteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by cuipengfei on 17-7-18.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserReadMapper userReadMapper;

    @Autowired
    private UserWriteMapper userWriteMapper;

    /**
     * 新增用户信息
     *
     * @param userPojo
     * @return
     */
    public int add(UserPojo userPojo) {
        userPojo.setCreateTime(new Date());
        userPojo.setUpdateTime(userPojo.getCreateTime());
        userPojo.setState((byte) 1);
        userPojo.setStatus((byte) 1);
        return userWriteMapper.add(userPojo);

    }

    /**
     * 根据用户名模糊查询用户信息
     *
     * @return
     */
    public List<UserPojo> getList(String userName) {
        return userReadMapper.getList(userName);
    }

    /**
     * 根据id更新用户信息
     *
     * @param userPojo
     * @return
     */
    public int updateUser(UserPojo userPojo) {
        userPojo.setUpdateTime(new Date());
        return userWriteMapper.update(userPojo);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public UserPojo getUserById(Long id) {
        return userReadMapper.getUserById(id);
    }

    /**
     * Propagation.REQUIRED隔离辅助测试方法
     *
     * @param id
     * @param descript
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactional_required_helper(long id, String descript) {
        UserPojo user = new UserPojo("Propagation.REQUIRED.userName", "Propagation.REQUIRED描述");
        this.add(user);
//        this.addUserLog_required(user.getId(), user.getDescript());
    }


}
