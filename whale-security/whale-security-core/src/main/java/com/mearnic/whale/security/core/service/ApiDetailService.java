package com.mearnic.whale.security.core.service;

import java.util.List;

/**
 * @author yqh
 * @since 2021-07-19
 */
public interface ApiDetailService {

    List<String> findAnonymity();

    List<String> findByUri(String uri);
}
