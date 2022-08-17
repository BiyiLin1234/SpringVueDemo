package com.example.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }


    @RequestMapping("/index")
    public String index() {
        System.out.println("hello");
        return "index";
    }
}
