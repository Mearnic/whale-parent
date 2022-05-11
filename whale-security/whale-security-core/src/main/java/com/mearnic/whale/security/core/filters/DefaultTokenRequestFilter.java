package com.mearnic.whale.security.core.filters;

import com.mearnic.whale.security.core.model.LoginUser;
import com.mearnic.whale.security.core.request.MutableHttpServletRequest;
import com.mearnic.whale.security.core.service.TokenService;
import com.mearnic.whale.security.core.service.UserDetailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class DefaultTokenRequestFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;

    private final UserDetailService userDetailService;

    public DefaultTokenRequestFilter(AuthenticationManager authenticationManager, TokenService tokenService, UserDetailService userDetailService) {
        super(authenticationManager);
        this.tokenService = tokenService;
        this.userDetailService = userDetailService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 获取token
        String token = request.getHeader("Authorization");

        // 鉴定token是否有效
        if (token == null || token.isEmpty()) {
            super.doFilterInternal(request, response, chain);
        } else {
            LoginUser user = tokenService.parseToken(token);
            String realToken = tokenService.getRealToken(user.getUsername());
            MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
            mutableRequest.putHeader("token", realToken);
            Set<GrantedAuthority> authorities = userDetailService.getAuthoritiesByUserKey(user.getKey());
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), realToken, authorities);
            //将Authentication对象放进springSecurity上下文中（进行认证操作）
            SecurityContextHolder.getContext().setAuthentication(authRequest);
            chain.doFilter(mutableRequest, response);
        }
    }
}
