package com.cuixingchen.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuipengfei on 17-6-15.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger= LoggerFactory.getLogger(DemoController.class);

    public String getDemo(){
        return "getDemo";
    }
}
