package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SignController {

    @Autowired
    private SignService signService;

    @GetMapping("api/storeSignInfo")
    public ResponseResult storeSignInfo(@RequestParam int u_id,@RequestParam String content){
       return signService.setSign(u_id, content);
    }

    @GetMapping("api/getSignInfo")
    public ResponseResult getSignInfo(){
        return signService.getAllSignInfo();
    }

    //判断是否签到

    @GetMapping("api/isSign")
    public  ResponseResult isSign(@RequestParam int u_id){
        return  signService.isSign(u_id);
    }

    //删除当天的签到
    @GetMapping("api/deleteSign")
    public  ResponseResult deleteSign(@RequestParam int u_id){
        return signService.deleteSign(u_id);
    }
}
