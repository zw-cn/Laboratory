package com.zw.video.java8;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间调整
 */
public class E19Adjust {
    /**
     * 时间调整
     */
    @Test
    void E19D1() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime adjNow = now.withDayOfMonth(10);//通过withXXX设置时间
        System.out.println(now);
        System.out.println(adjNow);

        LocalDateTime adjNow2 = now.with(TemporalAdjusters.firstDayOfNextYear());//明年第一天
        System.out.println(adjNow2);
        adjNow2 = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));//下周一
        System.out.println(adjNow2);

        //自定义日期时间调整
        now = now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDateTime workTime = now.with(t -> {
            LocalDateTime localDateTime = (LocalDateTime) t;
            DayOfWeek week = localDateTime.getDayOfWeek();
            if (DayOfWeek.FRIDAY.equals(week)) {
                return localDateTime.plusDays(3);
            }
            if (DayOfWeek.SATURDAY.equals(week)) {
                return localDateTime.plusDays(2);
            }
            if (DayOfWeek.SUNDAY.equals(week)) {
                return localDateTime.plusDays(1);
            }
            return localDateTime;
        });
        System.out.println(workTime);

    }
}
