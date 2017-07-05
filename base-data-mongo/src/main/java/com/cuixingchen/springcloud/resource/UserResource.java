package com.cuixingchen.springcloud.resource;

import com.cuixingchen.springcloud.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-21.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/user",description = "用户服务",produces = MediaType.APPLICATION_JSON)
public class UserResource {

    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    UserService userService;

    @GET
    @Path("/get")
    @ApiOperation(value = "get用户",httpMethod ="GET",notes = "get用户信息notes",position = 0)
    public Response get(@ApiParam(value = "用户名", required = true)@QueryParam("userName") String userName) {
        try {
            List<Map> userList = userService.get(userName);
            return Response.ok(userList).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            logger.error("user get异常:",e);
            return Response.status(202).build();
        }
    }
}
