package com.zw.video.java8;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Java 8日期相关
 */
public class E17Date {
    /**
     * SimpleDateFormat是线程不安全的
     */
    @Test
    void E17D1() throws ExecutionException, InterruptedException {
        System.out.println("SimpleDateFormat是线程不安全的");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        //多线程环境问题
        ExecutorService es = Executors.newFixedThreadPool(10);//创建线程池
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return simpleDateFormat.parse("20200908");
            }
        };
        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(es.submit(task));
        }
        for (Future<Date> result : results) {
            System.out.println(result.get());
        }
    }

    /**
     * 使用ThreadLocal解决SimpleDateFormat线程不安全问题
     */
    static class DateFormatThreadLocal{
        private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>(){
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyyMMdd");
            }
        };
        private static ThreadLocal<SimpleDateFormat> newThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));
        public static Date convert(String source) throws ParseException {
            return threadLocal.get().parse(source);
        }
    }
    @Test
    void E17D1S1() throws ExecutionException, InterruptedException {
        System.out.println("ThreadLocal解决SimpleDateFormat线程不安全问题");
        DateFormatThreadLocal dateFormatThreadLocal = new DateFormatThreadLocal();

        //多线程环境问题
        ExecutorService es = Executors.newFixedThreadPool(10);//创建线程池
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return dateFormatThreadLocal.convert("20200908");
            }
        };
        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(es.submit(task));
        }
        for (Future<Date> result : results) {
            System.out.println(result.get());
        }
    }

    /**
     * 使用LocalDate（Java8）解决线程不安全问题
     */
    @Test
    void E17D1S2() throws ExecutionException, InterruptedException {
        System.out.println("LocalDate是线程安全的");

        //多线程环境问题
        ExecutorService es = Executors.newFixedThreadPool(10);//创建线程池
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20210309",DateTimeFormatter.ofPattern("yyyyMMdd"));
            }
        };
        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(es.submit(task));
        }
        for (Future<LocalDate> result : results) {
            System.out.println(result.get());
        }
    }
}
