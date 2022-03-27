package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.springbootdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MybatisPlusUserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = getEntity();
        userMapper.insert(user);
        assertThat(user.getId()).isNotNull();
    }

    User getEntity() {
        return User.builder()
                .name("张三")
                .age(18)
                .email("xxx@email.com")
                .operator("test")
                .build();
    }
}
