package com.cuixingchen.springcloud.service;

import com.cuixingchen.springcloud.api.user.UserPojo;
import com.cuixingchen.springcloud.mapper.read.UserReadMapper;
import com.cuixingchen.springcloud.mapper.write.UserWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by cuipengfei on 17-7-18.
 */
@Service
public class UserService {

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
    public UserPojo getUserById(long id) {
        return userReadMapper.getUserById(id);
    }


    /**
     * 默认事务隔离级别
     * 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer transactional_required(long id, String descript, String ex) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setDescript("Propagation.REQUIRED:" + descript);
        Integer result = updateUser(userPojo);
        if (!StringUtils.isEmpty(ex)) {
            throw new RuntimeException();
        }
        return result;
    }

    /**
     * Propagation.REQUIRED隔离辅助测试方法
     *
     * @param id
     * @param descript
     * @param ex
     * @return
     */
    public Integer transactional_required_helper(long id, String descript, String ex) {
        UserPojo userPojo = new UserPojo();
        userPojo.setUserId("userId2");
        userPojo.setUserName("cuixingchen2");
        userPojo.setDescript("测试账户2");
        add(userPojo);
        return transactional_required(id, descript, ex);
    }

    /**
     * 支持当前事务，如果当前没有事务，就以非事务方式执行
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer transational_supports(long id, String descript, String ex) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setDescript("Propagation.SUPPORTS:" + descript);
        Integer result = updateUser(userPojo);
        if (!StringUtils.isEmpty(ex)) {
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional
    public Integer transational_supports_helper(long id, String descript, String ex) {
        UserPojo userPojo = new UserPojo();
        userPojo.setUserId("userId2");
        userPojo.setUserName("cuixingchen2");
        userPojo.setDescript("测试账户2");
        add(userPojo);
        return transational_supports(id, descript, ex);
    }

    /**
     * 支持当前事务，如果当前没有事务，就抛出异常
     *
     * @return
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public Object transational_mandatory() {
        return null;
    }

    /**
     * 新建事务，如果当前存在事务，把当前事务挂起
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object transational_requires_new() {
        return null;
    }

    /**
     * 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
     *
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Integer transational_not_supported(long id, String descript) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setDescript("Propagation.REQUIRED:" + descript);
        return updateUser(userPojo);
    }

    /**
     * 以非事务方式执行，如果当前存在事务，则抛出异常
     *
     * @return
     */
    @Transactional(propagation = Propagation.NEVER)
    public Object transational_never() {
        return null;
    }

    /**
     * @return
     */
    @Transactional(propagation = Propagation.NESTED)
    public Object transational_nested() {
        return null;
    }


}
