package com.mearnic.whale.security.core.component;

import com.mearnic.whale.security.core.model.DefaultGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author yqh
 * @since 2021-07-18
 */
@Component
public class DefaultAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            if (!"ROLE_ANONYMOUS".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("Not Login!");
                } else {
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    for (GrantedAuthority authority : authorities) {
                        if (authority instanceof DefaultGrantedAuthority) {
                            DefaultGrantedAuthority grantedAuthority = (DefaultGrantedAuthority) authority;
                            if (authority.getAuthority().equals(needRole)) {
                                return;
                            }
                        }
                    }
                }
                // 如果为访客,则判断是否是需要登录的接口,抛出指定异常
                throw new AccessDeniedException("Role Not Fund!");
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
