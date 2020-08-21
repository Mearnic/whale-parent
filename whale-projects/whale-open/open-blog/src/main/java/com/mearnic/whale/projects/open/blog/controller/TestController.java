package com.mearnic.whale.projects.open.blog.controller;

import com.mearnic.whale.projects.open.blog.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "testController")
@RequestMapping("/user")
public class TestController {

    @Resource
    private BlogService userService;
    @GetMapping(value = "/getUser")
    public String queryUserList() {
        return userService.test();
    }
}
