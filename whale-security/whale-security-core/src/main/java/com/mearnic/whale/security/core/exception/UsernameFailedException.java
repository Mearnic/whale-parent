package com.mearnic.whale.security.core.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class UsernameFailedException extends AccessDeniedException  {
    public UsernameFailedException(String msg) {
        super(msg);
    }
}
