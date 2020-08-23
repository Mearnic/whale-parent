package com.mearnic.whale.projects.open.blog.controller;

import com.mearnic.whale.projects.open.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "testController")
@RefreshScope
@RequestMapping
public class TestController {

    @Value("${logging.level.root}")
    private String profile;

    @Resource
    private BlogService userService;

    @GetMapping(value = "/file")
    public String queryUserList() {
        return profile;
    }
}
