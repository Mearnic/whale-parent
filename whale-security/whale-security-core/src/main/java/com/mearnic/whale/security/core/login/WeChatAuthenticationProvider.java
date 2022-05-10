package com.mearnic.whale.security.core.login;

import com.mearnic.whale.security.core.exception.CodeFailedException;
import com.mearnic.whale.security.core.model.WeChatUser;
import com.mearnic.whale.security.core.service.UserDetailService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yqh
 * @since 2021-04-18
 */
@Component
public class WeChatAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailService userDetailService;

    @Resource
    private WeChatLoginService weChatLoginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Object credentials = authentication.getCredentials();
//        if (credentials != null) {
//            String code = credentials.toString();
//            String openId = weChatLoginService.getOpenIdByCode(code);
//            UserDetails userDetails = userDetailService.getUserByThirdParty(openId, null);
//            WeChatUser weChatUser = new WeChatUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
//            return new WeChatAuthenticationToken(weChatUser);
//        }
        throw new CodeFailedException("无效的code, 请重新授权!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (WeChatAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
