package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.exception.UsernameFailedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author yqh
 * @since 2021-04-18
 */
@Service
public class AccountSecurityService implements UserDetailsService {

    @Resource
    private UserDetailService userDetailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameFailedException("用户名不能为空");
        } else {
            return userDetailService.findByUsername(username);
        }
    }
}
