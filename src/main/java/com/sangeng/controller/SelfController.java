package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SelfController {


    @Autowired
    private SelfService service;

    //获取当前用户的全部博客
@GetMapping("api/getSelfBok")
    public ResponseResult getSelfBok( @RequestParam int pageNum,@RequestParam int u_id){

   return service.getSelfBok(pageNum,u_id);
     }


     //获取当前用户的头像

    @GetMapping("api/getCurrentPeopleByUid")
    public  ResponseResult getCurrentPeopleByUid(@RequestParam int u_id){
            return service.getPeople(u_id);
    }

    @GetMapping("api/clickAttention")
    public ResponseResult clickAttention(@RequestParam int currentId,@RequestParam int attentionId){
      return service.clickAttention(currentId,attentionId);
    }

    @GetMapping("api/isAttention")
    public  ResponseResult isAttention(@RequestParam int currentId,@RequestParam int attentionId){
        return service.isAttention(currentId,attentionId);
    }


    @GetMapping("api/clickCollection")
    public ResponseResult clickCollection(@RequestParam int currentId,@RequestParam int attentionId){
        return service.clickCollection(currentId,attentionId);
    }

    @GetMapping("api/isCollection")
    public  ResponseResult isCollection(@RequestParam int currentId,@RequestParam int attentionId){
        return service.isCollection(currentId,attentionId);
    }

    //获取当前用户收藏的博客
    @GetMapping("api/getCollentionBok")
    public  ResponseResult getCollentionBok(@RequestParam int pageNum,@RequestParam int u_id){
         return service.getCollectionBok(pageNum,u_id);
    }

    //获取关注的列表
    @GetMapping("api/getAttention")
    public  ResponseResult getAttention(@RequestParam int pageNum, @RequestParam int u_id){
      return service.getAttention(pageNum,u_id);
    }

    //获取粉丝
    @GetMapping("api/getFans")
    public  ResponseResult  getFans(@RequestParam int pageNum,@RequestParam int u_id){
    return service.getFans(pageNum,u_id);
    }
}
