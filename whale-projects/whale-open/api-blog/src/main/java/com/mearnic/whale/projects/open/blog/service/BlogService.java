package com.mearnic.whale.projects.open.blog.service;

import com.mearnic.whale.projects.business.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    public User test(){
        return new User("mearnic");
    }
}
