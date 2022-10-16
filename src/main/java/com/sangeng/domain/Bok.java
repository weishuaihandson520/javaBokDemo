package com.sangeng.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_bok_list")
public class Bok {

    //博客的id
    @TableId("b_id")
    private  int b_id;

    //当前用户的id
    private  int u_id;

    //文章的标题
    private  String title;

    //文章的主题内容
    private  String content;

    //评论数
    private  int comments;
    //点赞数
    private  int goods;
    //阅读数
    private int views;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private  Date  bok_create_time;

    //多对一(多个文章对应一个user)
    private User user;

    public Bok(int u_id, String title, String content,Date bok_create_time) {
        this.u_id = u_id;
        this.title = title;
        this.content = content;
        this.bok_create_time=bok_create_time;
    }
}
