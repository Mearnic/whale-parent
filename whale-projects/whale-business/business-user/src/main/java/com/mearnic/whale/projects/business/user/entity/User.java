package com.mearnic.whale.projects.business.user.entity;

import lombok.Data;

@Data
public class User {
    private String name;

    public User(String name){
        this.name = name;
    }
}
