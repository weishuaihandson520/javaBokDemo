package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.SelfBok;
import com.sangeng.domain.collection;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionMapper extends BaseMapper<collection> {

    @Select("select u.id, u.avatar,b.b_id,b.title,b.bok_create_time from sys_bok_collection c join sys_bok_list b on c.b_id=b.b_id join sys_user u on b.u_id=u.id where c.u_id=#{u_id}")
    Page<SelfBok> queryCollectionBok(@Param("page") Page page, @Param("u_id") int u_id);
}
