package com.cuixingchen.springcloud.service;

/**
 * 断路器demo service
 * <p>
 * Created by cuipengfei on 17-6-14.
 */
public interface HystrixDemoService {
    /**
     * 根据传参param获取消息
     *
     * @param param
     * @return
     */
    String getMessage(String param);


    String defaultMessage(String param);

    default String getName(String name) {
        return "defalut" + name;
    }
}
