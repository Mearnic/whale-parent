package com.mearnic.whale.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mearnic.whale.security.core.bean.EStatus;
import com.mearnic.whale.security.core.bean.R;
import org.springframework.security.core.Authentication;
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

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String result = objectMapper.writeValueAsString(R.ok(EStatus.LOGIN_OUT_SUCCESS));
        response.getWriter().write(result);
    }
}
