package com.cuixingchen.springcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by cuipengfei on 17-7-10.
 */
@Controller
@RequestMapping(value = "/session")
public class RedisSessionController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String add(HttpServletRequest request) {
        request.getSession().setAttribute("login", "OK");
        return "登录成功";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("login");
        return obj != null ? obj.toString() : "NULL";
    }
}
