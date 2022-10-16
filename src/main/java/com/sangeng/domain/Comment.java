package com.sangeng.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName("sys_bok_comments")
public class Comment {

    @TableId("c_id")
    private  int c_id;
    private  int b_id;

    private  int u_id;

    @TableField("createCommentTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createCommentTime;

    private  String content;


    private User user;

    public Comment( int b_id, int u_id, Date dataString, String comments) {
        this.b_id=b_id;
        this.u_id=u_id;
        this.createCommentTime=dataString;
        this.content=comments;

    }
}
