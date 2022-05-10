package com.mearnic.whale.security.core.service;

import com.mearnic.whale.security.core.model.LoginUser;
import org.springframework.security.core.userdetails.User;

public interface TokenService {
    String createToken(User user);
    LoginUser parseToken(String token);
    String getRealToken(String userName);
    boolean deleteToken(String userName);
}
