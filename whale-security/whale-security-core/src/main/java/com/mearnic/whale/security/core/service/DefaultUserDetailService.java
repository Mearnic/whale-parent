package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.model.DefaultGrantedAuthority;
import com.mearnic.whale.security.core.model.LoginUser;
import com.mearnic.whale.security.core.model.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yqh
 * @since 2021-04-18
 */
@Service
public class DefaultUserDetailService implements UserDetailService {

    @Resource
    protected UserService userService;

    @Override
    public UserDetails findByUsername(String username) {
        LoginUser user = userService.findByUsername(username);
        return new UserDetail(user.getUsername(), user.getPassword(), getAuthoritiesByUserKey(user.getKey()));
    }

    /**
     * 根据用户id 获取用户所拥有的角色
     *
     * @param key 用户key
     * @return Set<SimpleGrantedAuthority>
     */
    @Override
    public Set<GrantedAuthority> getAuthoritiesByUserKey(String key) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userService.getUserRolesByKey(key).forEach(it -> authorities.add(new DefaultGrantedAuthority(it.getName())));
        return authorities;
    }
}
