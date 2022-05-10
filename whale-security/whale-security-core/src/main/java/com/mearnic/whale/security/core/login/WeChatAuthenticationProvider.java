package com.mearnic.whale.security.core.login;

import com.mearnic.whale.security.core.exception.CodeFailedException;
import com.mearnic.whale.security.core.model.LoginRole;
import com.mearnic.whale.security.core.model.LoginUser;
import com.mearnic.whale.security.core.model.WeChatUser;
import com.mearnic.whale.security.core.service.UserDetailService;
import com.mearnic.whale.security.core.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yqh
 * @since 2021-04-18
 */
@Component
public class WeChatAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object credentials = authentication.getCredentials();
        if (credentials != null) {
            String code = credentials.toString();
            // TODO 第三方登录并根据返回的uuid查找用户
            UserDetails userDetails = userDetailService.getUserByThirdParty("admin", "1");
            WeChatUser weChatUser = new WeChatUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            return new WeChatAuthenticationToken(weChatUser);
        }
        throw new CodeFailedException("无效的code, 请重新授权!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (WeChatAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
