package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.SelfBok;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelfMapper extends BaseMapper {

    @Select(" select u.id,u.avatar,b.b_id,b.title,b.bok_create_time from sys_bok_list b join sys_user u on b.u_id =u.id where b.u_id=#{u_id} order by b.bok_create_time desc")
    Page<SelfBok> querySelfBok(@Param("page") Page page, @Param("u_id") int u_id);
}
