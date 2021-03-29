package com.zw.video.java8;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Java8 日期时间API使用
 *
 * ISO-8601标准：2011-12-03T10:15:30
 */
public class E18LocalDateTimeAPI {
    /**
     * 日期时间API使用
     * 1.LocalDate
     * 2.LocalTime
     * 3.LocalDateTime
     */
    @Test
    void E18D1LocalDate() {
        //获取当前时间
        LocalDateTime time1 = LocalDateTime.now();
        System.out.println(time1);
        //指定时间
        LocalDateTime time2 = LocalDateTime.of(2021, 3, 29, 21, 36, 55);
        System.out.println(time2);
        //日期运算
        LocalDateTime time3 = LocalDateTime.now();
        LocalDateTime time4 = time3.plusDays(10);//日期加10天
        System.out.println(time4);
        LocalDateTime time5 = time3.minusYears(3);//日期减3年
        System.out.println(time5);
        //日期项获取
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear());
        System.out.println(now.getMonth().getValue()+" OR "+now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
    }

    /**
     * Instant 时间戳
     * Unix元年 1970-01-01 00:00:00起的毫秒数
     */
    @Test
    void E18D1Instant() {
        Instant instant1 = Instant.now();
        System.out.println(instant1);//默认UTC时区

        OffsetDateTime offsetDateTime = instant1.atOffset(ZoneOffset.ofHours(8));//偏移量时间戳
        System.out.println(offsetDateTime);

        //获取毫秒数
        long second = offsetDateTime.toEpochSecond();
        System.out.println(second);

        //获取指定毫秒数时间
        Instant milli = Instant.ofEpochSecond(3600);
        System.out.println(milli);
    }
}
