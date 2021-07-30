package com.mearnic.whale.security.core.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class CodeFailedException extends AccessDeniedException  {
    public CodeFailedException(String msg) {
        super(msg);
    }
}
