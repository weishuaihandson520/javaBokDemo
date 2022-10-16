package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.sangeng.domain.Bok;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BokMapper extends BaseMapper<Bok> {
   Page selectBokByPage(@Param("page") Page page);

   List<Bok> selectBokByCurrentDay();

   Bok selectBokAndUserInfoByBid(@Param("b_id") int b_id);
}
