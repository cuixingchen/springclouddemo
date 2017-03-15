package com.cuixingchen.springcloud.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by cuipengfei on 17-3-14.
 */
@Path("/redis")
@Produces(MediaType.APPLICATION_JSON)
public class RedisDemoResource {
    private Logger logger= LoggerFactory.getLogger(UserResource.class);

    @Autowired
//    private RedisTemplate template;

    @GET
    @Path("/getList")
    public Response getList(){
//        logger.info("----getList----"+list);
        return Response.ok("成功").type(MediaType.APPLICATION_JSON).build();
    }
}
