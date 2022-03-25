package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public int create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }

    @PostMapping("/update")
    public int update(@RequestBody User user) {
        return userService.updateById(user);
    }

    @GetMapping("/delete")
    public int delete(Long id) {
        return userService.deleteById(id);
    }
}
