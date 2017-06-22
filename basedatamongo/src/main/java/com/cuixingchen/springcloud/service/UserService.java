package com.cuixingchen.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-21.
 */
@Service
public class UserService {
    @Autowired
    MongoTemplate mongoTemplate;

    @GET
    @Path("/get")
    public List<Map> get() {
        return mongoTemplate.find(new Query(Criteria.where("goodsId").is("123")), Map.class, "goods");
    }
}
