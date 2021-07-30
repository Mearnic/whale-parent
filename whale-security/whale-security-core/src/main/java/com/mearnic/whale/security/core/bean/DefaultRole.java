package com.mearnic.whale.security.core.bean;


import com.mearnic.whale.security.core.model.LoginRole;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class DefaultRole implements LoginRole {

    private String name;

    public DefaultRole(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
