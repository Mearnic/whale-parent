package com.mearnic.whale.security.core.config;

//import com.mearnic.whale.cloud.security.core.component.DefaultObjectPostProcessor;

import com.mearnic.whale.security.core.constant.PackageConstant;
import com.mearnic.whale.cloud.security.core.response.*;
import com.mearnic.whale.security.core.response.*;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    List<AuthenticationProvider> authenticationProviders;

    @Resource
    DefaultAccessDeniedHandler defaultAccessDeniedHandler;
    @Resource
    DefaultLogoutSuccessHandler defaultLogoutSuccessHandler;
    @Resource
    DefaultAuthenticationEntryPoint defaultAuthenticationEntryPoint;
    @Resource
    DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;
    @Resource
    DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Resource
    FilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource;
    @Resource
    AccessDecisionManager defaultAccessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<AbstractAuthenticationProcessingFilter> filters = createFilters();
        filters.forEach(it -> http.addFilterBefore(it, UsernamePasswordAuthenticationFilter.class));
        http.authorizeRequests().withObjectPostProcessor(getObjectPostProcessor())
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
        // TODO 如果是security服务, 需要开启跳转登录页面, 不能重构.(重构需要自己实现登录页面逻辑)
        http.exceptionHandling().authenticationEntryPoint(defaultAuthenticationEntryPoint);
        http.exceptionHandling().accessDeniedHandler(defaultAccessDeniedHandler);
        http.logout().logoutSuccessHandler(defaultLogoutSuccessHandler);
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        authenticationProviders.forEach(auth::authenticationProvider);
    }

    public ObjectPostProcessor<FilterSecurityInterceptor> getObjectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(defaultFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(defaultAccessDecisionManager);
                return o;
            }
        };
    }


    /**
     * 获取配置 {@link PackageConstant} `LOGIN_FILTERS_PACKAGE` 路径下的所有登录过滤器
     *
     * @return List<AbstractAuthenticationProcessingFilter> 登录过滤器列表
     * @throws Exception 当 authenticationManager() 执行失败时
     */
    public List<AbstractAuthenticationProcessingFilter> createFilters() throws Exception {
        AuthenticationManager authenticationManager = authenticationManager();
        Reflections reflections = new Reflections(PackageConstant.LOGIN_FILTERS_PACKAGE);
        Set<Class<? extends AbstractAuthenticationProcessingFilter>> types = reflections.getSubTypesOf(AbstractAuthenticationProcessingFilter.class);
        return types.stream().map(it -> {
            AbstractAuthenticationProcessingFilter filter = null;
            try {
                filter = it.newInstance();
                filter.setAuthenticationManager(authenticationManager);
                filter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
                filter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
            } catch (Exception e) {
                // TODO logger error
            }
            return filter;
        }).collect(Collectors.toList());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
}