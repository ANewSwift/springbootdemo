package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getDetail")
    public User getDetail() {
        return User.builder().id(1l).name("张三").age((short) 18).build();
    }
}
