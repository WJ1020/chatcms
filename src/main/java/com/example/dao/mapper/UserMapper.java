package com.example.dao.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/2.
 * 数据库操作类
 */
@Mapper
public interface UserMapper {
    int save(User user);
    User findById(Integer id);
    User findByUserName(String username);
    List<User> findAll();
}
