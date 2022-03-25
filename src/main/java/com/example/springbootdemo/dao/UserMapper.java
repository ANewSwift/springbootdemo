package com.example.springbootdemo.dao;


import com.example.springbootdemo.domain.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Repository
public interface UserMapper {
    int insert(User user);
    User get(Long id);
    int updateById(User user);
    int deleteById(Long id);
}
