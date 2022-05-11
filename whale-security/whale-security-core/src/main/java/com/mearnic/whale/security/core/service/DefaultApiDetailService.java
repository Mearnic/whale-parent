package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.bean.DefaultApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yqh
 * @since 2021-07-19
 */
@Service
public class DefaultApiDetailService implements ApiDetailService {

    @Value("${spring.application.name}")
    private  String serverName;

    @Override
    public List<DefaultApi> findAnonymity() {
        ArrayList<DefaultApi> urls = new ArrayList<>();
        urls.add(new DefaultApi("/v3","GET", serverName));
        urls.add(new DefaultApi("/login","GET", serverName));
        urls.add(new DefaultApi("/oauth/authorize","GET", serverName));
        urls.add(new DefaultApi("/oauth/token","GET", serverName));
        urls.add(new DefaultApi("/favicon.ico","GET", serverName));
        return urls;
    }

    @Override
    public List<String> findByUri(String uri, String method, String serverName) {
        ArrayList<String> roles = new ArrayList<>();
        if (uri.equals("/v1")) {
            roles.add("ROLE_TEST");
        }
        roles.add("ROLE_ADMIN");
        return roles;
    }
}
