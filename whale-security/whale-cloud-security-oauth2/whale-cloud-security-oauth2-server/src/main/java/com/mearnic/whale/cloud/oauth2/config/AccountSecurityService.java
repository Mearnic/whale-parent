package com.mearnic.whale.cloud.oauth2.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yqh
 * @since 2021-04-18
 */
//@Service
public class AccountSecurityService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String u) throws UsernameNotFoundException {
        return new User("admin", passwordEncoder.encode("1234"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("product, product:list"));
    }
}
