package com.mearnic.whale.security.core.filters;

import com.mearnic.whale.security.core.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DefaultTokenRequestFilter extends BasicAuthenticationFilter {

    private Integer tokenExpireTime;

    private TokenService tokenService;

    public DefaultTokenRequestFilter(AuthenticationManager authenticationManager, TokenService tokenService, Integer tokenExpireTime) {
        super(authenticationManager);
        this.tokenExpireTime = tokenExpireTime;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        Set<GrantedAuthority> authorities = new HashSet<>();
        String token = request.getHeader("token");
        String name = tokenService.parseToken(token);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, "tiger");
        //将Authentication对象放进springsecurity上下文中（进行认证操作）
        SecurityContextHolder.getContext().setAuthentication(authRequest);

//		super.doFilterInternal(request, response, chain);
        chain.doFilter(request, response);
    }
}
