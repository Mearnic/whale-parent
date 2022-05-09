package com.mearnic.whale.security.core.service;

import org.springframework.security.core.userdetails.User;

public interface TokenService {
    String createToken(User userDetail);
    String parseToken(String token);
}
