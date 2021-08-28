package com.mearnic.whale.cloud.oauth2.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes("authorizationRequest")
public class GrantController {

    @RequestMapping("/confirm_access")
    public String getAccessConfirmation(Map<String, Object> model) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        model.put("clientId", authorizationRequest.getClientId());
        model.put("scopes", authorizationRequest.getScope());
        return "base-grant";
    }

}  