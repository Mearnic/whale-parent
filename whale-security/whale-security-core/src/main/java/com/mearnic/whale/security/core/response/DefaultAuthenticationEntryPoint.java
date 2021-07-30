package com.mearnic.whale.security.core.response;

import com.mearnic.whale.security.core.constant.PackageConstant;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 触发条件
 * 1、自定义认证过滤器不能继承{@link AbstractAuthenticationProcessingFilter}
 * 2、自定义认证过滤器应在ExceptionTranslationFilter后面
 * {@link PackageConstant} `LOGIN_FILTERS_PACKAGE` 下所有filter都不触发
 */
@Component
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("JSON_用户没有登录");
    }
}
