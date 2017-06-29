package com.cuixingchen.springcloud.mapper.write;

import com.cuixingchen.springcloud.mapper.BaseWriteMapper;

import java.util.Map;

/**
 * Created by cuipengfei on 17-6-29.
 */
public interface UserWriteMapper extends BaseWriteMapper{

    Integer add(Map<String,Object> user);
}
