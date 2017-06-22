package com.cuixingchen.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-21.
 */
@Service
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 根据用户名称查询用户集合
     *
     * @param userName
     * @return
     */
    public List<Map> get(String userName) {
        return mongoTemplate.find(new Query(Criteria.where("userName").is(userName)), Map.class, "user");
    }
}
