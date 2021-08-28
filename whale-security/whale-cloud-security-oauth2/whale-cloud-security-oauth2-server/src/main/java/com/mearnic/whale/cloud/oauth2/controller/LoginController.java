package com.mearnic.whale.cloud.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model){

        model.addAttribute("loginProcessUrl","/authorize");

        return "base-login";
    }

}