package com.cuixingchen.springcloud.mapper.write;

import com.cuixingchen.springcloud.api.user.UserPojo;
import com.cuixingchen.springcloud.mapper.BaseWriteMapper;

import java.util.Map;

/**
 * Created by cuipengfei on 17-6-29.
 */
public interface UserWriteMapper extends BaseWriteMapper{

    int add(UserPojo userPojo);

    int update(UserPojo userPojo);
}
