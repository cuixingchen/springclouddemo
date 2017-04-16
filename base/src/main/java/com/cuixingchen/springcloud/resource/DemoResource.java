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
@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {

    private Logger logger= LoggerFactory.getLogger(DemoResource.class);

    @GET
    @Path("/get")
    public Response get(){
        logger.info("----调用成功----");
        return Response.ok("成功").type(MediaType.APPLICATION_JSON).build();
    }
}
