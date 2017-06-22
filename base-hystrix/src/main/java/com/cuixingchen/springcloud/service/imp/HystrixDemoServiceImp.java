package com.cuixingchen.springcloud.service.imp;

import com.cuixingchen.springcloud.service.HystrixDemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by cuipengfei on 17-6-14.
 */
@Service
public class HystrixDemoServiceImp implements HystrixDemoService {


    private static final Random random = new Random();

    @HystrixCommand(fallbackMethod = "defaultGet")
    @Override
    public String get(String param) {
        int randomInt = random.nextInt(10);
        if (randomInt < 8) {
            throw new RuntimeException("call HystrixDemoService getMessage fail.");
        }
        return "入参：" + param + ",调用成功";
    }

    @Override
    public String defaultGet(String param) {
        return "断路器返回结果："+param;
    }


}
