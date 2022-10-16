package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.User;
import com.sangeng.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;


    //登录
    @PostMapping("/api/login")
    public ResponseResult login(User user){
       //登录
        System.out.println(user);
        return loginService.login(user);
    }
    //退出
    @RequestMapping("/api/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    //获取用户的信息
    @GetMapping("/api/info")
    public ResponseResult userInfo(){
//        System.out.println(userName);
    return    loginService.getUserInfo();
    }


    @GetMapping("api/checkToken")
    public ResponseResult checkUser(){
        return null;
    }
}
