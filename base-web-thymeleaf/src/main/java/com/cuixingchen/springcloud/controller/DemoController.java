package com.cuixingchen.springcloud.controller;

import com.cuixingchen.springcloud.feign.UserFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by cuipengfei on 17-6-15.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    UserFeign userFeign;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(Model model) {
        try {
            ResponseEntity<List<Map>> result = userFeign.get("");
            model.addAttribute("name","哈哈");
        }catch (Exception e){
            logger.error("demo get异常",e);
        }
        return "demo/demo_list";
    }
}
