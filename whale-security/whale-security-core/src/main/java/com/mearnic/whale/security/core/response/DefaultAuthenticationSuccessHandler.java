package com.mearnic.whale.security.core.response;

import com.mearnic.whale.security.core.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yqh
 * @since 2021-07-19
 */
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        User userDetail = (User) authentication.getPrincipal();
//        String attribute = request.getAttribute(CsrfToken.class.getName()).toString();
        response.getWriter().write("JSON_SUCCESS:" + tokenService.createToken(userDetail));


//        User userDetail = (User) authentication.getPrincipal();
//        String passWord = userDetail.getPassword();
//
//        response.setContentType("application/json;charset=UTF-8");
//        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//
////        response.getWriter().write("JSON_SUCCESS");
//        response.getWriter().write(userDetail.getUsername() + "-" + token);

    }
}
