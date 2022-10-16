package com.sangeng.service;


import com.sangeng.domain.ResponseResult;

//签到
public interface SignService {

    ResponseResult setSign(int u_id,String content);

    ResponseResult getAllSignInfo();


    ResponseResult isSign(int u_id);


    ResponseResult deleteSign(int u_id);
}
