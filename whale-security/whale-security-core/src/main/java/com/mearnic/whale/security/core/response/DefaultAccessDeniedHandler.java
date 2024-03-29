package com.mearnic.whale.security.core.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mearnic.whale.security.core.bean.CommonCode;
import com.mearnic.whale.security.core.bean.EStatus;
import com.mearnic.whale.security.core.bean.Result;
import com.mearnic.whale.security.core.bean.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        String result = objectMapper.writeValueAsString(Result.error(CommonCode.DATA_UNAUTH));
        response.getWriter().write(result);
    }
}
