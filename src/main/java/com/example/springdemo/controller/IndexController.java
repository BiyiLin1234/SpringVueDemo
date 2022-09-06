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

    @RequestMapping("/vue_template")
    public String vueTemplate() {
        return "vue_template";
    }

    @RequestMapping("/vue_dynamic_bind")
    public String vueDynamicBind() {
        return "vue_dynamic_bind";
    }

    @RequestMapping("/compute_attribute")
    public String vueComputeAttribute() {
        return "day2/compute_attribute";
    }

    @RequestMapping("/")
    public String defaultLearnEnglish() {
        return "/learn_english";
    }
}
