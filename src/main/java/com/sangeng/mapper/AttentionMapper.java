package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.Attention;
import com.sangeng.domain.AttentionAndFans;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttentionMapper extends BaseMapper<Attention> {

    @Select("select u.id,u.nick_name,u.avatar,u.create_time from sys_self_attention a join sys_user u on a.attention_id=u.id where a.u_id=#{u_id}")
    Page<AttentionAndFans> queryAttention(@Param("page") Page page, @Param("u_id") int u_id);

    @Select("select u.id,u.nick_name,u.avatar,u.create_time from sys_self_attention a join sys_user u on a.u_id=u.id where a.attention_id=#{u_id}")
    Page<AttentionAndFans> queryFans(@Param("page") Page page,@Param("u_id") int u_id);

}
