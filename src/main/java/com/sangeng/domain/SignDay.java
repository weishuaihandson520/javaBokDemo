package com.sangeng.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_sign_day")
public class SignDay {

    private int u_id;

    private Date sign_time;

    private String sign_content;
}
