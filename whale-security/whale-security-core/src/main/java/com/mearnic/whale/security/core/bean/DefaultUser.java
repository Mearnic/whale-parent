package com.mearnic.whale.security.core.bean;


import com.mearnic.whale.security.core.model.LoginUser;

/**
 * @author yqh
 * @since 2021-04-18
 */
public class DefaultUser implements LoginUser {

    private Long id;

    private String username;

    private String password;

    public DefaultUser() {
    }

    public DefaultUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getKey() {
        return getId() + "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
