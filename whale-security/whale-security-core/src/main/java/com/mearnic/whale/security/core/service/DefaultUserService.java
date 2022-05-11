package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.bean.DefaultRole;
import com.mearnic.whale.security.core.bean.DefaultUser;
import com.mearnic.whale.security.core.model.LoginRole;
import com.mearnic.whale.security.core.model.LoginUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yqh
 * @since 2021-07-19
 */
@Service
public class DefaultUserService implements UserService {

    @Override
    public LoginUser findByUsername(String username) {
        DefaultUser user = new DefaultUser();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("tiger");
        return user;
    }

    @Override
    public List<LoginRole> getUserRolesByKey(String key) {
        DefaultRole role = new DefaultRole("ROLE_TEST");
        ArrayList<LoginRole> userRoles = new ArrayList<>();
        userRoles.add(role);
        return userRoles;
    }

    @Override
    public String getUserNameByThirdParty(String openId, String type) {
        return "admin";
    }
}
