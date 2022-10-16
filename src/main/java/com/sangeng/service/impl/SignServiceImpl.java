package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.SignCount;
import com.sangeng.domain.SignDay;
import com.sangeng.domain.Sign_Day_Count;
import com.sangeng.mapper.SignCountMapper;
import com.sangeng.mapper.SignDayMapper;
import com.sangeng.service.SignService;
import com.sangeng.utils.getCurrentTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SignServiceImpl implements SignService {


    @Autowired
    private SignDayMapper signDayMapper;

    @Autowired
    private SignCountMapper signCountMapper;

    //存储当天的签到信息
    @Override
    public ResponseResult setSign(int u_id, String content) {


        Timestamp timeStamp = new getCurrentTime().getTime();


        int insert = signDayMapper.insert(new SignDay(u_id, timeStamp, content));
        //如果插入成功,则将总的签到记录+1
        if(insert ==1){
            //先看看有没有当前用户之前的签到记录
            //如果有的话，则 update ,没有的话就insert 置1
            QueryWrapper<SignCount> sign= new QueryWrapper<>();
            sign.eq("u_id",u_id);
            boolean exists = signCountMapper.exists(sign);
            //存在的话，当前数据+1
            if(exists){
                 UpdateWrapper<SignCount> signupdate = new UpdateWrapper<>();
                 signupdate.setSql("sign_count=sign_count+1");
                 signCountMapper.update(null,signupdate);
            }
            //不存在就insert 置1
            else{
                 signCountMapper.insert(new SignCount(u_id,1));
            }
        }
        return insert==1?new ResponseResult<>(200,"签到成功"):new ResponseResult<>(200,"签到失败");
    }


    //获取当天的签到信息(userInfo:{ 用户的u_id ,用户的头像,用户的名字 } 当天的签到时间 当天的签到内容 总签到数)
    @Override
    public ResponseResult getAllSignInfo() {
        List sign_day_counts = signCountMapper.selectAllCountInfo();
        return  sign_day_counts.isEmpty()?new ResponseResult(200,"无签到信息"):new ResponseResult(200,"查询成功",sign_day_counts);
    }


    @Override
    public ResponseResult isSign(int u_id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u_id",u_id);
        boolean exists = signDayMapper.exists(queryWrapper);
        return exists?new ResponseResult<>(200,"用户已签到"):new ResponseResult<>(200,"用户未签到");
    }

    @Override
    public ResponseResult deleteSign(int u_id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("u_id",u_id);
        int delete = signDayMapper.delete(queryWrapper);

        //删除之后总签到数-1
       if(delete==1){
           UpdateWrapper updateWrapper = new UpdateWrapper();
           updateWrapper.eq("u_id",u_id);
           updateWrapper.setSql("sign_count=sign_count-1");
           signCountMapper.update(null,updateWrapper);
       }

        return delete==1?new ResponseResult(200,"删除成功"):new ResponseResult(200,"删除失败");
    }
}
