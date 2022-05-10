package com.mearnic.whale.security.core.login;

import com.mearnic.whale.security.core.model.WeChatUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class WeChatAuthenticationToken extends AbstractAuthenticationToken {

    private String code;
    private WeChatUser weChatUser;

    public WeChatAuthenticationToken(String code) {
        super(null);
        this.code = code;
        setAuthenticated(true);
    }

    public WeChatAuthenticationToken(WeChatUser weChatUser) {
        super(null);
        this.weChatUser = weChatUser;
        setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return code;
    }

    @Override
    public WeChatUser getPrincipal() {
        return weChatUser;
    }


}
