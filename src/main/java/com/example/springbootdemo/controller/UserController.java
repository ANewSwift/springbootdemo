package com.example.springbootdemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haoshaolong
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("create")
    public Boolean create(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("batchCreate")
    public Boolean batchCreate(@RequestBody List<User> users) {
        return userService.saveBatch(users);
    }

    @GetMapping("getById")
    public User getById(Long id) {
        return userService.getById(id);
    }

    @PostMapping("page")
    public IPage<User> page() {
        return userService.page(new Page<>());
    }

    @PostMapping("updateById")
    public Boolean updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    @PostMapping("deleteByIds")
    public Boolean deleteByIds(@RequestBody List<Long> ids) {
        return userService.removeByIds(ids);
    }
}
