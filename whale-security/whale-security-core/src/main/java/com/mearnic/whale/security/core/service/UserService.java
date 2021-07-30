package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.model.LoginRole;
import com.mearnic.whale.security.core.model.LoginUser;

import java.util.List;

/**
 * @author yqh
 * @since 2021-04-18
 */
public interface UserService {

    LoginUser findByUsername(String username);

    List<LoginRole> getUserRolesByKey(String key);

}
