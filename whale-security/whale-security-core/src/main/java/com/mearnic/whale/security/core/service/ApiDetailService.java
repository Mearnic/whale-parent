package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.bean.DefaultApi;

import java.util.List;

/**
 * @author yqh
 * @since 2021-07-19
 */
public interface ApiDetailService {

    List<DefaultApi> findAnonymity();

    List<String> findByUri(String uri, String method, String serverName);
}
