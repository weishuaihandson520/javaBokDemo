package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.goods;
import com.sangeng.domain.goodsToUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookGoods extends BaseMapper<goods> {

List<goodsToUser>  goodsUserByUid (@Param("b_id") int b_id);
}
