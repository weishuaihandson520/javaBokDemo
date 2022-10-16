package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.*;
import com.sangeng.mapper.AttentionMapper;
import com.sangeng.mapper.CollectionMapper;
import com.sangeng.mapper.SelfMapper;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class SelfServiceImpl implements SelfService {



    @Autowired
    private SelfMapper selfMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AttentionMapper attentionMapper;

    @Autowired
    private CollectionMapper collectionMapper;
    //获取当前用户的文章
    @Override
    public ResponseResult getSelfBok(int page1,int u_id) {
        Page page = new Page(page1,10);
        Page selfBoks = selfMapper.querySelfBok(page,u_id);
        List list = selfBoks.getRecords();
        long current = selfBoks.getCurrent();
        long totalPage = selfBoks.getPages();

        HashMap map = new HashMap();
        map.put("records",list);
        map.put("currentPage",current);
        map.put("page_count",totalPage);
        return new ResponseResult(200,"查询成功",map);
    }

    @Override
    public ResponseResult getPeople(int u_id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",u_id);
        User one = userMapper.selectOne(queryWrapper);

        return new ResponseResult(200,"查询成功",one.getAvatar());
    }

    //单点关注
    @Override
    public ResponseResult clickAttention(int currentId, int attentionId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u_id",currentId);
        queryWrapper.eq("attention_id",attentionId);
        boolean exists = attentionMapper.exists(queryWrapper);

        //如果存在的话,再点击就是取消关注
        if(exists){
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper.eq("u_id",currentId);
            queryWrapper.eq("attention_id",attentionId);
            int delete = attentionMapper.delete(queryWrapper1);
            return delete==1?new ResponseResult(200,"取消成功"):new ResponseResult(200,"取消失败");
        }

        //不存在就添加
      else{
            int insert = attentionMapper.insert(new Attention(currentId, attentionId));

            return insert==1?new ResponseResult(200,"关注成功"):new ResponseResult(200,"关注失败");
        }
    }

    //是否关注

    @Override
    public ResponseResult isAttention(int currentId, int attentionId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u_id",currentId);
        queryWrapper.eq("attention_id",attentionId);
        boolean exists = attentionMapper.exists(queryWrapper);
        return exists?new ResponseResult<>(200,"当前博客已关注"):new ResponseResult(200,"用户没有关注");
    }


    @Override
    public ResponseResult clickCollection(int currentId, int attentionId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u_id",currentId);
        queryWrapper.eq("b_id",attentionId);
        boolean exists = collectionMapper.exists(queryWrapper);

        if(exists){
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper.eq("u_id",currentId);
            queryWrapper.eq("b_id",attentionId);
            int delete = collectionMapper.delete(queryWrapper1);
            return delete==1?new ResponseResult(200,"取消收藏成功"):new ResponseResult(200,"取消失败");
        }

        //不存在就添加
        else{
            int insert = collectionMapper.insert(new collection(currentId, attentionId));

            return insert==1?new ResponseResult(200,"收藏成功"):new ResponseResult(200,"收藏失败");
        }

    }

    @Override
    public ResponseResult isCollection(int currentId, int attentionId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u_id",currentId);
        queryWrapper.eq("b_id",attentionId);
        boolean exists = collectionMapper.exists(queryWrapper);
        return exists?new ResponseResult<>(200,"当前博客已收藏"):new ResponseResult(200,"用户没有收藏");
    }

    //获取当前的收藏
    @Override
    public ResponseResult getCollectionBok( int pageNum,int u_id) {
        Page page = new Page(pageNum,10);
        Page selfBoks = collectionMapper.queryCollectionBok(page,u_id);
        List list = selfBoks.getRecords();
        long current = selfBoks.getCurrent();
        long totalPage = selfBoks.getPages();

        HashMap map = new HashMap();
        map.put("records",list);
        map.put("currentPage",current);
        map.put("page_count",totalPage);
        return new ResponseResult(200,"查询成功",map);
    }

    @Override
    public ResponseResult getAttention(int pageNum,int u_id) {
        Page page = new Page(pageNum,10);
        Page selfBoks = attentionMapper.queryAttention(page,u_id);
        List list = selfBoks.getRecords();
        long current = selfBoks.getCurrent();
        long totalPage = selfBoks.getPages();

        HashMap map = new HashMap();
        map.put("records",list);
        map.put("currentPage",current);
        map.put("page_count",totalPage);
        return new ResponseResult(200,"查询成功",map);
    }


    @Override
    public ResponseResult getFans(int pageNum, int u_id) {
        Page page = new Page(pageNum,10);
        Page<AttentionAndFans> attentionAndFansPage = attentionMapper.queryFans(page, u_id);

        List list = attentionAndFansPage.getRecords();
        long current = attentionAndFansPage.getCurrent();
        long totalPage = attentionAndFansPage.getPages();

        HashMap map = new HashMap();
        map.put("records",list);
        map.put("currentPage",current);
        map.put("page_count",totalPage);
        return new ResponseResult(200,"查询成功",map);
    }
}
