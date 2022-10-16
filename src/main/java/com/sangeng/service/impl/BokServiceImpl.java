package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.*;
import com.sangeng.mapper.BokComments;
import com.sangeng.mapper.BokMapper;
import com.sangeng.mapper.BokViews;
import com.sangeng.mapper.BookGoods;
import com.sangeng.service.BokService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class BokServiceImpl implements BokService {



    @Autowired
    private BokMapper bokMapper;


    @Autowired
    private BookGoods bookGoods;


    @Autowired
    private BokComments bokComments;


    @Autowired
    private BokViews bokViews;

    @Override
    public ResponseResult bokList( int pageNum) {
        Page page = new Page(pageNum,10);
        Page page1 = bokMapper.selectBokByPage(page);
        List list = page1.getRecords();
        long current = page1.getCurrent();
        long totalPage = page1.getPages();

        HashMap map = new HashMap();
        map.put("records",list);
        map.put("currentPage",current);
        map.put("page_count",totalPage);
        return new  ResponseResult(200,"查询成功",map);
    }

    @Override
    public ResponseResult allBokList() {
        List<Bok> boks = bokMapper.selectBokByCurrentDay();

        return new  ResponseResult(200,"查询成功",boks);
    }


    //点赞之后存用户信息到表中,将goods+1(可以重复点赞，但是再次点赞之后，就goods-1)
    @Override
    public ResponseResult goods(int b_id,int u_id) {
        //先判断当前文章用户是否点赞

        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("b_id", b_id);
        queryWrapper.eq("u_id", u_id);

        goods goods = bookGoods.selectOne(queryWrapper);

        //如果在就-1,
        if (goods!=null) {
            //在的话，再次点击就goods-1

            QueryWrapper<goods> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("b_id", b_id).eq("u_id", u_id);
            //移除当前点赞用户
            int delete = bookGoods.delete(queryWrapper1);


            UpdateWrapper<Bok> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("b_id", b_id);
            updateWrapper.setSql("goods = goods - 1");
            bokMapper.update(null, updateWrapper);

            return delete==1? new ResponseResult(200, "取消成功"):null;




        }
        //存入到用户信息到点赞表中
        int insert = bookGoods.insert(new goods(b_id, u_id));
        //点赞书-1
        UpdateWrapper<Bok> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("b_id", b_id);
        updateWrapper.setSql("goods = goods + 1");
        bokMapper.update(null, updateWrapper);

        return insert==1? new ResponseResult(200, "点赞成功"):null;
    }

    @Override
    public ResponseResult goodsUserInfo(int b_id) {

        HashMap map = new HashMap();
        List avatarList = new ArrayList();
        List<goodsToUser> list = bookGoods.goodsUserByUid(b_id);
        list.stream().forEach(i->{
            avatarList.add(i.getUser().getAvatar());
        });

        map.put("avatarList",avatarList);
        return new ResponseResult(200,"查询成功",map);

    }

    @Override
    public ResponseResult getComments(int b_id) {

        List<Comment> list = bokComments.selectComments(b_id);


        return new ResponseResult(200,"查询完成",list);
    }


    @Override
    public ResponseResult setComments(int b_id,int u_id,String comments) throws ParseException {

        Date date = new Date();

        Timestamp timeStamp = new Timestamp(date.getTime());

        int insert = bokComments.insert(new Comment(b_id, u_id, timeStamp, comments));



             //插入成功就将sys_bok_list+1
            UpdateWrapper<Bok> queryWrapper = new UpdateWrapper<>();
            queryWrapper.eq("b_id",b_id);
            queryWrapper.setSql("comments=comments+1");
            int update = bokMapper.update(null, queryWrapper);


        return  new ResponseResult(200,"评论插入完成");
    }

    @Override
    public ResponseResult deleteComment(int c_id,int b_id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id",c_id);
        int delete = bokComments.delete(queryWrapper);

        //删除就减1
        UpdateWrapper<Bok> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("b_id",b_id);
        updateWrapper.setSql("comments=comments-1");
        int update = bokMapper.update(null, updateWrapper);

        return new ResponseResult(200,"删除成功");
    }


    //浏览数

    @Override
    public ResponseResult setViews(int b_id, int u_id) {
        //先看数据库是否有当前用户对当前博客的浏览
        QueryWrapper<View> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("b_id",b_id).eq("u_id",u_id);
        boolean exists = bokViews.exists(queryWrapper);

        //如果存在就不+1
        if(!exists){
            //插入数据
            int insert = bokViews.insert(new View(u_id, b_id));
            //主库的views+1
          if(insert==1){
              UpdateWrapper<Bok> updateWrapper = new UpdateWrapper<>();
              updateWrapper.eq("b_id",b_id);
              updateWrapper.setSql("views=views+1");
              bokMapper.update(null,updateWrapper);
          }
          return new ResponseResult<>(200,"浏览成功");
        }
        else {
            return new ResponseResult<>(200,"浏览失败");
        }
    }

    @Override
    public ResponseResult setTopic(int u_id,String title, String content) {
        Date date = new Date();

        Timestamp timeStamp = new Timestamp(date.getTime());

        int insert = bokMapper.insert(new Bok(u_id, title, content,timeStamp));

        return insert==1?new ResponseResult<>(200,"插入成功"):new ResponseResult<>(200,"插入失败");
    }


    //通过bId获取当前的博客信息

    @Override
    public ResponseResult getCurrentBok(int b_id) {
//        QueryWrapper queryWrapper = new QueryWrapper();
////        queryWrapper.eq("b_id",b_id);
////        Bok bok = bokMapper.selectOne(queryWrapper);
        Bok bok = bokMapper.selectBokAndUserInfoByBid(b_id);
        return bok!=null?new ResponseResult<>(200,"查询成功",bok): null;
    }
}





