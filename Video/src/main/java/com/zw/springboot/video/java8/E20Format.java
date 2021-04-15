package com.zw.springboot.video.java8;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * 日期时间格式化
 * 时区API
 */
public class E20Format {
    /**
     * 日期字符串之间的转化
     */
    @Test
    void E20D1E1() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter isoDate = DateTimeFormatter.ISO_DATE;
        System.out.println(isoDate.format(now));
        //自定义日期格式化
        DateTimeFormatter patternFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//HH和hh的区别：HH24小时制，hh12小时制
        System.out.println(patternFormatter.format(now));

        //将格式化日期转换为时间
        LocalDateTime parseTime = LocalDateTime.parse("2021/04/01 12:01:01", patternFormatter);
        System.out.println(parseTime);
    }

    @Test
    void E20D1E2() {
        //展示所有时区
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::print);

        //按照时区获取时间
        LocalDateTime usTime = LocalDateTime.now(ZoneId.of("US/Central"));
        System.out.println(usTime);
        ZonedDateTime zonedDateTime = usTime.atZone(ZoneId.of("US/Central"));
        System.out.println(zonedDateTime);

    }
}
