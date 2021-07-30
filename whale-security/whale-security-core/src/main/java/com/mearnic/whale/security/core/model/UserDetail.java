package com.mearnic.whale.security.core.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class UserDetail extends User {

    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
