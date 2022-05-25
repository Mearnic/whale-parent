package com.mearnic.whale.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mearnic.whale.security.core.bean.CommonCode;
import com.mearnic.whale.security.core.bean.EStatus;
import com.mearnic.whale.security.core.bean.R;
import com.mearnic.whale.security.core.bean.Result;
import com.mearnic.whale.security.core.model.LoginUser;
import com.mearnic.whale.security.core.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getHeader("Authorization");
        String result;
        if (request.getHeader("Authorization") == null || token.isEmpty()) {
            result = objectMapper.writeValueAsString(Result.error(CommonCode.TOKEN_ERROR));
        } else {
            LoginUser loginUser = tokenService.parseToken(token);
            boolean isOk = tokenService.deleteToken(loginUser.getUsername());
            if (isOk) {
                result = objectMapper.writeValueAsString(Result.error(CommonCode.LOGOUT_SUCCESS));
            }else{
                result = objectMapper.writeValueAsString(Result.error(CommonCode.TOKEN_ERROR));
            }
        }
        response.getWriter().write(result);
    }
}
