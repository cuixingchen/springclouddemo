package com.cuixingchen.springcloud.mapper.read;

import com.cuixingchen.springcloud.api.user.UserPojo;
import com.cuixingchen.springcloud.mapper.BaseReaderMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-29.
 */
public interface UserReadMapper extends BaseReaderMapper {

    List<UserPojo> getList(@Param("userName") String userName);

    UserPojo getUserById(@Param("id") long id);
}
