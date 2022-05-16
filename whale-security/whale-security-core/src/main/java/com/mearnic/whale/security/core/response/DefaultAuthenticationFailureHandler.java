package com.mearnic.whale.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mearnic.whale.security.core.bean.EStatus;
import com.mearnic.whale.security.core.bean.R;
import com.mearnic.whale.security.core.bean.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String result = objectMapper.writeValueAsString(Result.error(EStatus.LOGIN_EXCEPTION.toString()));
        response.getWriter().write(result);
    }
}
