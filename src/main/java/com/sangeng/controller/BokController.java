package com.sangeng.controller;


import com.sangeng.domain.ResponseResult;
import com.sangeng.service.BokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@CrossOrigin
public class BokController {
    @Autowired
    private BokService bokService;


    //获取当前的页的博客消息
    @GetMapping("api/bok")
    public ResponseResult getBokByPage(@RequestParam int pageNum){
        System.out.println(pageNum);
      return  bokService.bokList(pageNum);
    }


    //获取当天的博客信息
    @GetMapping("api/allBok")
    public ResponseResult getAllBok(){
        return  bokService.allBokList();
    }

    //点赞
    @GetMapping("api/goods")
    public  ResponseResult changeGoods(@RequestParam int b_id,@RequestParam int u_id){

        return bokService.goods(b_id,u_id);
    }

    //通过当前博客，查看用户的头像(在前端展示出来)
    @GetMapping("api/getGoodsInfo")
    public  ResponseResult getAvatarByUid(@RequestParam int b_id){
        return bokService.goodsUserInfo(b_id);
    }


    //通过当前博客查询评论信息
    @GetMapping("api/getCommentsInfoByBid")
    public  ResponseResult getCommentInfo(@RequestParam int b_id){
        return  bokService.getComments(b_id);
    }


    @GetMapping("api/setComment")
    public  ResponseResult setComment(@RequestParam int b_id,@RequestParam int u_id,@RequestParam String comment) throws ParseException {
        return bokService.setComments(b_id,u_id,comment);
    }

    @GetMapping("api/deleteComment")
    public  ResponseResult deleteComment(@RequestParam int c_id,@RequestParam int b_id){
        return bokService.deleteComment(c_id,b_id);
    }

    @GetMapping("api/setView")
    public  ResponseResult setView(@RequestParam int b_id,@RequestParam int u_id){
        return bokService.setViews(b_id,u_id);
    }

    @GetMapping("api/setTopic")
    public  ResponseResult setTopic(@RequestParam int u_id,@RequestParam String title,@RequestParam String content){
        return bokService.setTopic(u_id,title,content);
    }

    //点击查询当前的博客
    @GetMapping("api/getbokBybid")
    public ResponseResult  getbokBybid(@RequestParam int b_id){
        return bokService.getCurrentBok(b_id);
    }
}
