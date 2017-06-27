package com.cuixingchen.springcloud.feign;

import com.cuixingchen.springcloud.feign.hystrix.UserFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-22.
 */
@FeignClient(name = "base-data-mongo", fallback = UserFallback.class)
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public interface UserFeign {
    @GET
    @Path("/get")
    ResponseEntity<List<Map>> get(@QueryParam("userName") String userName);

}
