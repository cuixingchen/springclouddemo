package com.cuixingchen.springcloud.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by cuipengfei on 17-2-23.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private Logger logger= LoggerFactory.getLogger(UserResource.class);

    @GET
    @Path("/getList")
    public Response getList(){
        logger.info("----getList----");
        return Response.ok("成功").type(MediaType.APPLICATION_JSON).build();
    }
}
