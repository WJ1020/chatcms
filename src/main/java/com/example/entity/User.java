package com.example.entity;

import com.example.annotation.Excel;

import java.io.Serializable;

/**
 * Created by WangShiXiang on 2017/5/2.
 * 用户类
 */
public class User implements Serializable{
    @Excel(name = "id")
    private Integer id;
    @Excel(name = "姓名")
    private String username;
    @Excel(name="密码")
    private String password;
    @Excel(name="邮箱")
    private String email;
    @Excel(name="手机")
    private String phone;

    public User(){}
    public User(Integer id, String username, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
