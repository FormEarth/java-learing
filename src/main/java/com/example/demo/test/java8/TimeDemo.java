package com.example.demo.test.java8;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.jetbrains.annotations.NotNull;

import com.example.demo.global.Common;

/**
 * java8中时间类的相关操作<br>
 * Instant相当于是一个时间戳
 *
 * @author qiyuan
 * @date 2022-1-27 10:54:38
 */
public class TimeDemo {

    /**
     * LocalDateTime -> Date
     *
     * @param time
     * @return
     */
    static Date getDate(@NotNull LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     * @return
     */
    static LocalDateTime getLocalDateTime(@NotNull Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * format LocalDateTime
     *
     * @param time
     * @return
     */
    static String formatLocalDateTime(LocalDateTime time) {
        return Common.DATE_FORMATTER.format(time);
    }

    /**
     * LocalDateTime常用计算
     *
     * @return
     */
    static void computeLocalDateTime() {

        LocalDateTime fix = LocalDateTime.of(2022, 1, 1, 12, 12);
        LocalDateTime time = LocalDateTime.now();
        // 计算天数增减后时间
        time.plusDays(1);
        // 计算时间间隔
        Duration.between(fix, time).toDays();
        Duration.between(fix, time).toHours();
    }

    static void dateFormat() {
        //日期
        LocalDate ld = LocalDate.now();//2019-12-20
        System.out.println(ld);
        //日期时间
        LocalDateTime ldt = LocalDateTime.now();//2019-12-20T10:37:06.834
        System.out.println(ldt);
        //格式化,日期时间->字符串
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = ldt.format(dtf);//2019-12-20 10:40:10
        System.out.println(str);
        //格式化,字符串->日期时间
        ldt = LocalDateTime.parse("2019-12-20 10:40:10", dtf);
        System.out.println(ldt);
    }

    static void compute() {
        // 根据配置生成cron表达式
        int i = 10000, hour, minute, second;
        hour = i / 3600;
        minute = (i - hour * 3600) / 60;
        second = (i - hour * 3600) % 60;
        System.out.println(String.format("%s %s %s", hour, minute, second));
    }

    public static void main(String[] args) {
        compute();
    }

}
