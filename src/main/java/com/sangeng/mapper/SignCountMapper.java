package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.Sign_Day_Count;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SignCountMapper extends BaseMapper<com.sangeng.domain.SignCount> {
    @Select("   select u.id,u.avatar,u.nick_name,d.sign_time,d.sign_content,c.sign_count from sys_sign_day d join sys_sign_count c on d.u_id=c.u_id join sys_user u on u.id=d.u_id")
    List<Sign_Day_Count> selectAllCountInfo();
}
