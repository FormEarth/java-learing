package com.example.demo.test.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 新的时间日期
 *
 * @author raining_heavily
 * @date 2019/12/20 10:33
 **/
public class DateTime {
    static void  date(){
        //日期
        LocalDate ld = LocalDate.now();//2019-12-20
        System.out.println(ld);
        //日期时间
        LocalDateTime ldt = LocalDateTime.now();//2019-12-20T10:37:06.834
        System.out.println(ldt);
        //格式化,日期时间->字符串
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String  str  = ldt.format(dtf);//2019-12-20 10:40:10
        System.out.println(str);
        //格式化,字符串->日期时间
        ldt = LocalDateTime.parse("2019-12-20 10:40:10",dtf);
        System.out.println(ldt);

    }

    public static void main(String[] args) {
//        date();
        System.out.println(System.currentTimeMillis());
    }
}
