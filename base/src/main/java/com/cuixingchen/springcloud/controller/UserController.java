package com.cuixingchen.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cuipengfei on 17-2-23.
 */
@RestController
public class UserController {

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getUser(){
        List<Goods> list = mongoTemplate.find(new Query(Criteria.where("goodsId").is("123")), Goods.class, "goods");
        return "hi user"+list;
    }
}
