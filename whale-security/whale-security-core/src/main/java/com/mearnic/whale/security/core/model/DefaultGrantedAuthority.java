package com.mearnic.whale.security.core.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author yqh
 * @since 2021-07-19
 */
public class DefaultGrantedAuthority implements GrantedAuthority {

    private String role;
    private String method;
    private String project;

    public DefaultGrantedAuthority(String role) {
        this.role = role;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
