package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import org.springframework.stereotype.Service;

import java.text.ParseException;


public interface BokService {

    //获取文章的所有列表
    ResponseResult bokList(int pageNum);


    //获取当天的博客
    ResponseResult allBokList();

    //点赞
    ResponseResult goods(int b_id,int u_id);


    ResponseResult goodsUserInfo(int b_id);

    ResponseResult getComments(int b_id);

    ResponseResult setComments(int b_id,int u_id,String comment) throws ParseException;

    ResponseResult deleteComment(int c_id,int b_id);

    ResponseResult setViews(int b_id,int u_id);

    ResponseResult setTopic(int u_id,String title,String content);

    ResponseResult getCurrentBok(int b_id);
}
