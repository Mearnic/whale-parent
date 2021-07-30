package com.mearnic.whale.security.core.login.filters;

import com.mearnic.whale.security.core.login.WeChatAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class WeChatRequestFilter extends AbstractAuthenticationProcessingFilter {

    private final static String REQUEST_PATH_LOGIN = "/weChat";
    private final static String REQUEST_PATH_TYPE = "GET";

    public WeChatRequestFilter() {
        super(new AntPathRequestMatcher(REQUEST_PATH_LOGIN, REQUEST_PATH_TYPE));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String code = request.getParameter("code");
        return this.getAuthenticationManager().authenticate(new WeChatAuthenticationToken(code));
    }

}
