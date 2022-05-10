package com.mearnic.whale.security.core.filters;

import com.mearnic.whale.security.core.exception.UsernameFailedException;
import com.mearnic.whale.security.core.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DefaultTokenRequestFilter extends BasicAuthenticationFilter {

    private TokenService tokenService;

    public DefaultTokenRequestFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        Set<GrantedAuthority> authorities = new HashSet<>();
        String token = request.getHeader("token");

        if (token == null || token.isEmpty()) {
            super.doFilterInternal(request, response, chain);
        } else {
            String name = tokenService.parseToken(token);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(name, "tiger");
//        authRequest = null;
            //将Authentication对象放进springsecurity上下文中（进行认证操作）
            SecurityContextHolder.getContext().setAuthentication(authRequest);

//		super.doFilterInternal(request, response, chain);
            chain.doFilter(request, response);
        }

    }
}
