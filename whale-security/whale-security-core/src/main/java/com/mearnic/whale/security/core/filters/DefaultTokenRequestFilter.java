package com.mearnic.whale.security.core.filters;

import com.mearnic.whale.security.core.model.LoginUser;
import com.mearnic.whale.security.core.request.MutableHttpServletRequest;
import com.mearnic.whale.security.core.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultTokenRequestFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;

    public DefaultTokenRequestFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 获取token
        String token = request.getHeader("token");

        // 鉴定token是否有效
        if (token == null || token.isEmpty()) {
            super.doFilterInternal(request, response, chain);
        } else {
            LoginUser user = tokenService.parseToken(token);
            String realToken = tokenService.getRealToken(user.getUsername());
            MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
            mutableRequest.putHeader("token", realToken);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            //将Authentication对象放进springSecurity上下文中（进行认证操作）
            SecurityContextHolder.getContext().setAuthentication(authRequest);
            chain.doFilter(mutableRequest, response);
//            chain.doFilter(request, response);
        }
        // 鉴定用户信息是否修改
        // 如果token有效且未修改,直接处理权限问题
        // 如果token有效但是修改了,创建新的token替代
    }
}
