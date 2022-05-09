package com.mearnic.whale.security.core.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecuritySession {
    public static synchronized User getUser() {
        User principal = null;
        try {
            principal = (User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception e) {
        }
        return principal;
    }
}
