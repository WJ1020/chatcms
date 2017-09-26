package com.example.server;

import com.example.dao.mapper.UserMapper;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/2.
 * 登录信息服务
 */
@Service
public class UserServer {
    private UserMapper userMapper;
    @SuppressWarnings("all")//因为idea不能自动识别，提示以下的bean不存在
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public int save(User user){
        return this.userMapper.save(user);
    }
    public User findById(Integer id){
        return this.userMapper.findById(id);
    }
    public User findByUserName(String username){
        return this.userMapper.findByUserName(username);
    }
    public List<User> findAll(){
        return this.userMapper.findAll();
    }
}
