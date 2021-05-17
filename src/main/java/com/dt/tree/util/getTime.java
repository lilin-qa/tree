package com.dt.tree.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class getTime {

    private static final Logger gaiaLogger = LoggerFactory.getLogger(MyHttpRequestUtils.class);



    /**
     * 获取当前时间yyy-MM-dd  HH:mm:ss
     * @param dt
     * @return
     * @throws ParseException
     */
    public  static  Date getNowDate(Date dt) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        String date=formatDate.format(dt);
        System.out.println("getTime当前时间："+formatDate.parse(date));
        return  formatDate.parse(date);
    }

    public  static  String getNowDateString(Date dt) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        String date=formatDate.format(dt);
        System.out.println("getTime当前时间："+formatDate.parse(date));
        return  date;
    }
    public static void main(String[] args) throws ParseException {
       System.out.println( getNowDate(new Date()));
    }
}
