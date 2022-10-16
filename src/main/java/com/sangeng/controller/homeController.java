package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {


    //TODO 到后台页面，只能admin用户访问(后期设置)

    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
   @GetMapping("/home")
    public ResponseResult gohome(){
         return new ResponseResult(200,"ok");
    }
}
