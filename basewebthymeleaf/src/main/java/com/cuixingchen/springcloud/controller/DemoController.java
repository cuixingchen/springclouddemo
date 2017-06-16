package com.cuixingchen.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cuipengfei on 17-6-15.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "/getDemoList", method = RequestMethod.GET)
    public String getDemoList(Model model) {
        model.addAttribute("name","哈哈");
        return "user/user_list";
    }
}
