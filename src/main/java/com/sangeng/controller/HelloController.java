package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')") //自定义权限校验
//    @PreAuthorize("hasAnyAuthority('admin','test','system:dept:list')") //多个权限，只需满足一个权限就能访问
//    @PreAuthorize("hasRole('system:dept:list')") //有一个ROLE_的前缀，数据库的信息必须要有前缀(如RELE_system:dept:list)
//    @PreAuthorize("hasAnyRole('admin','system:dept:list')") //传入多个权限，满足一个就行
    public String hello(){
        return "hello";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200,"testCors");
    }
}
