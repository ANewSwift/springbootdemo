package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.User;

public interface UserService {
    int create(User user);
    User get(Long id);
    int updateById(User user);
    int deleteById(Long id);
}
