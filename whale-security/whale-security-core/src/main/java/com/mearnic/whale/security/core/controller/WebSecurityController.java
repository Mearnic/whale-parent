package com.mearnic.whale.security.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SpringSecurity 核心请求
 *
 * @author Mercurial
 */
@Controller
public class WebSecurityController {

    @RequestMapping("/v1")
    @ResponseBody
    public String v1() {
        return "v1";
    }

    @RequestMapping("/v2")
    @ResponseBody
    public String v2() {
        return "v2";
    }

    @RequestMapping("/v3")
    @ResponseBody
    public String v3() {
        return "v3";
    }

}  