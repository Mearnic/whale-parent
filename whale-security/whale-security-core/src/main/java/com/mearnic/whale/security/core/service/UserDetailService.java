package com.mearnic.whale.security.core.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * @author yqh
 * @since 2021-04-18
 */
public interface UserDetailService {

    UserDetails findByUsername(String username);

    Set<GrantedAuthority> getAuthoritiesByUserKey(String key);

}
