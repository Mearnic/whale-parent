package com.mearnic.whale.security.core.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yqh
 * @since 2021-07-19
 */
@Service
public class DefaultApiDetailService implements ApiDetailService {
    @Override
    public List<String> findAnonymity() {
        ArrayList<String> urls = new ArrayList<>();
        urls.add("/v3");
        urls.add("/login");
        urls.add("/oauth/authorize");
        urls.add("/oauth/token");
        urls.add("/favicon.ico");
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
