package com.sangeng.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_bok_collection")
@Alias("tb_collection")
public class collection {

    private int   u_id;

    private  int b_id;


}
