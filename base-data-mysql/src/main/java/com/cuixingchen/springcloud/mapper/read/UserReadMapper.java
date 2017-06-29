package com.cuixingchen.springcloud.mapper.read;

import com.cuixingchen.springcloud.mapper.BaseReaderMapper;

import java.util.Map;

/**
 * Created by cuipengfei on 17-6-29.
 */
public interface UserReadMapper extends BaseReaderMapper {

    Map<String, Object> getList();
}
