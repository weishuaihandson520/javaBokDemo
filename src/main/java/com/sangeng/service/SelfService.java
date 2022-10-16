package com.sangeng.service;

import com.sangeng.domain.ResponseResult;

public interface SelfService {

    ResponseResult getSelfBok( int page,int u_id);

    ResponseResult getPeople(int u_id);

    ResponseResult clickAttention(int currentId,int attentionId);
    ResponseResult isAttention (int currentId,int attentionId);

    ResponseResult clickCollection(int currentId,int attentionId);

    ResponseResult isCollection(int currentId,int attentionId);

    ResponseResult getCollectionBok(int pageNum ,int u_id);

    ResponseResult getAttention( int pageNum,int u_id);

    ResponseResult getFans(int pageNum,int u_id);
}
