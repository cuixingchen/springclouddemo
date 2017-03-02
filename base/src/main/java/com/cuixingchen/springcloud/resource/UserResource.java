package com.cuixingchen.springcloud.resource;

import com.cuixingchen.springcloud.controller.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by cuipengfei on 17-2-23.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private Logger logger= LoggerFactory.getLogger(UserResource.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @GET
    @Path("/getList")
    public Response getList(){
        List<Goods> list = mongoTemplate.find(new Query(Criteria.where("goodsId").is("123")), Goods.class, "goods");
        logger.info("----getList----"+list);
        return Response.ok("成功").type(MediaType.APPLICATION_JSON).build();
    }
}
