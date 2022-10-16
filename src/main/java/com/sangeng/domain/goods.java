package com.sangeng.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_bok_goods")
public class goods {

    @TableId("id")
    private  int id;

    private  int b_id;

    private  int u_id;


    public goods( int b_id, int u_id) {
         this.b_id=b_id;
         this.u_id=u_id;
    }
}
