package com.cuixingchen.springcloud.mapper.write;

import com.cuixingchen.springcloud.api.user.UserLog;
import com.cuixingchen.springcloud.mapper.BaseWriteMapper;

/**
 * Created by cuipengfei on 17-7-19.
 */
public interface UserLogWriteMapper extends BaseWriteMapper {

    int add(UserLog userLog);
}
