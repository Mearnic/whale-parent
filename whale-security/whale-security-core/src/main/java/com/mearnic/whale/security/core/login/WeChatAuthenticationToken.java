package com.mearnic.whale.security.core.login;

import com.mearnic.whale.security.core.model.SecurityUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class WeChatAuthenticationToken extends AbstractAuthenticationToken {

    private String code;
    private SecurityUser securityUser;

    public WeChatAuthenticationToken(String code) {
        super(null);
        this.code = code;
        setAuthenticated(true);
    }

    public WeChatAuthenticationToken(SecurityUser securityUser) {
        super(null);
        this.securityUser = securityUser;
        setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return code;
    }

    @Override
    public SecurityUser getPrincipal() {
        return securityUser;
    }


}
