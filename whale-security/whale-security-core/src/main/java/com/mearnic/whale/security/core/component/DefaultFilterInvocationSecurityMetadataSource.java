package com.mearnic.whale.security.core.component;

import com.mearnic.whale.security.core.service.ApiDetailService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author yqh
 * @since 2021-07-18
 */
@Component
public class DefaultFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private ApiDetailService apiDetailService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) object;
        String uri = invocation.getRequest().getRequestURI();
        List<String> anonymityList = apiDetailService.findAnonymity();
        if (anonymityList.contains(uri)) {
            return null;
        } else {
            List<String> roles = apiDetailService.findByUri(uri);
            return SecurityConfig.createList(roles.toArray(new String[0]));
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
