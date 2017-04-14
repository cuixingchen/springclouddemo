package com.cuixingchen.springcloud.resource;

import com.mongodb.MongoClientOptions;
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
import java.util.Map;

/**
 * Created by cuipengfei on 17-4-14.
 */
@Path("/mongodemo")
@Produces(MediaType.APPLICATION_JSON)
public class MongoDemoResource {

    private Logger logger= LoggerFactory.getLogger(MongoDemoResource.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @GET
    @Path("/query")
    public Response getList(){
        List<Map> list = mongoTemplate.find(new Query(Criteria.where("goodsId").is("123")),Map.class, "goods");
        logger.info("----getList----"+list);
        return Response.ok("成功"+list).type(MediaType.APPLICATION_JSON).build();
    }
}
