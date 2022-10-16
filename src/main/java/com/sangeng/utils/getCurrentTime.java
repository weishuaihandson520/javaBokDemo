package com.sangeng.utils;

import java.sql.Timestamp;
import java.util.Date;

public class getCurrentTime {

    public Timestamp getTime(){
        //获取当前时间
        Date date = new Date();

        return  new Timestamp(date.getTime());
    }
}
