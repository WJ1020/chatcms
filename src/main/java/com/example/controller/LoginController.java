package com.example.controller;

import com.example.entity.User;
import com.example.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by WangShiXiang on 2017/5/2.
 * 登录服务器
 */
@RestController
@RequestMapping("user")
public class LoginController {

    private UserServer userServer;
    @Autowired
    public void setUserServer(UserServer userServer) {
        this.userServer = userServer;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession){
        User user=userServer.findByUserName(username);
        if (user!=null){
            if (user.getPassword().equals(password)){
                httpSession.setAttribute("user",user);
                return "success";
            }
        }
        return "error";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public User user_message(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if (user!=null){
            return user;
        }
        return null;
    }
    @RequestMapping(value = "/login",method = RequestMethod.DELETE)
    public String quit(HttpSession httpSession){
        httpSession.invalidate();
        return "success";
    }
}
