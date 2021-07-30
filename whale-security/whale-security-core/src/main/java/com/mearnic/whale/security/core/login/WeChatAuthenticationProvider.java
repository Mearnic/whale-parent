package com.mearnic.whale.security.core.login;

import com.mearnic.whale.security.core.exception.CodeFailedException;
import com.mearnic.whale.security.core.model.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author yqh
 * @since 2021-04-18
 */
@Component
public class WeChatAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object credentials = authentication.getCredentials();
        if (credentials != null) {
            String code = credentials.toString();
            // TODO 第三方登录并根据返回的uuid查找用户

            SecurityUser securityUser = new SecurityUser();
            return new WeChatAuthenticationToken(securityUser);
        }
        throw new CodeFailedException("无效的code, 请重新授权!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (WeChatAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
