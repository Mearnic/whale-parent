package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.exception.UsernameFailedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        if (StringUtils.isNotBlank(username)) {
            return userDetailService.findByUsername(username);
        } else {
            throw new UsernameFailedException("用户名不能为空");
        }
    }
}
