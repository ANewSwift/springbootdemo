package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author haoshaolong
 * @since 2022-03-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
