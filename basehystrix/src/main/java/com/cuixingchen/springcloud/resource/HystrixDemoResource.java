package com.cuixingchen.springcloud.resource;

import com.cuixingchen.springcloud.service.HystrixDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by cuipengfei on 17-6-14.
 */
@Path(value = "/hystrix")
@Produces(value = MediaType.APPLICATION_JSON)
public class HystrixDemoResource {
    private Logger logger = LoggerFactory.getLogger(HystrixDemoResource.class);

    @Autowired
    private HystrixDemoService hystrixDemoService;

    @GET
    @Path("/get/{param}")
    public Response get(@PathParam(value = "param") String param) {
        String result = hystrixDemoService.get(param);
        return Response.ok(result).type(MediaType.APPLICATION_JSON).build();
    }


}
