package com.mearnic.whale.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mearnic.whale.security.core.bean.EStatus;
import com.mearnic.whale.security.core.bean.R;
import com.mearnic.whale.security.core.service.TokenService;
import jdk.nashorn.internal.parser.JSONParser;
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

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        User userDetail = (User) authentication.getPrincipal();
        String token = tokenService.createToken(userDetail);
        String result = objectMapper.writeValueAsString(R.ok(EStatus.LOGIN_SUCCESS).data(token));
        response.getWriter().write(result);
    }
}
