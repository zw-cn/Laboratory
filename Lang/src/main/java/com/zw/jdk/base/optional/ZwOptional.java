package com.zw.jdk.base.optional;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional 使用
 * Optional is primarily intended for use as a method return type where there is a clear need to represent "no result," and where using null is likely to cause errors.
 * A variable whose type is Optional should never itself be null;
 * it should always point to an Optional instance
 * use:用于替代那些可能返回没结果的方法返回值
 * This is a value-based class; use of identity-sensitive operations (including reference equality (==), identity hash code, or synchronization) on instances of Optional may have unpredictable results and should be avoided
 * warn:别用 ==, hashcode, synchronize这些操作optional 实例
 */
@Slf4j
public class ZwOptional {
    /**
     * 创建操作
     */
    @Test
    void create() {
        log.info("Optional 的创建");
        Optional<Object> o1 = Optional.empty();
        log.info("创建 空 Optional->{}", o1);

        Optional<String> o2 = Optional.of("2");
        log.info("创建 of Optional->{}", o2);
//        Optional<Object> o3 = Optional.of(null);//给null会导致运行报空指针
//        log.info("创建 of Optional->{}",o3);

        Optional<Object> o4 = Optional.ofNullable(null);//可以为 null
        log.info("创建 ofNullable Optional->{}", o4);
    }

    @Test
    void decide() {
        Optional<String> a = Optional.of("A");
        Optional<String> b = Optional.of("B");
        Optional<String> c = Optional.of("A");
        log.info("Optional equals a=b->{},a=c->{}", a.equals(b), a.equals(c));

        Optional<Object> empty = Optional.empty();
        Optional<Object> notEmpty = Optional.of("A");
        log.info("Optional isEmpty empty-{},notEmpty-{}", empty.isEmpty(), notEmpty.isEmpty());

        log.info("Optional isPresent empty-{},notEmpty-{}", empty.isPresent(), notEmpty.isPresent());
    }

    @Test
    void values() {
        Optional<Object> empty = Optional.empty();
        Optional<Object> notEmpty = Optional.of("A");

        /*
        直接取值
         */
//        log.info("Optional get empty-{}", empty.get());//java.util.NoSuchElementException: No value present
        log.info("Optional get notEmpty-{}", notEmpty.get());

        /*
        非空则consumer接口消费
         */
        empty.ifPresent(o -> log.info("Optional ifPresent empty-{}", o.getClass()));
        notEmpty.ifPresent(o -> log.info("Optional ifPresent notEmpty-{}", o.getClass()));
        /*
        非空则consumer接口消费，否则Runnable运行,但是不能改原值
         */
        empty.ifPresentOrElse(o -> log.info("Optional ifPresentOrElse empty-{}", o), () -> log.info("Optional ifPresentOrElse empty-{}", empty.of("B")));
        log.info("empty-{}",empty);
        notEmpty.ifPresentOrElse(o -> log.info("Optional ifPresentOrElse notEmpty-{}", o), () -> log.info("Optional ifPresentOrElse notEmpty-{}", notEmpty.of("B")));

        /*
        or
        有就直接返回Optional，否则通过Supplier接口获取一个Optional
         */
        log.info("Optional or empty-{}", Optional.empty().or(() -> Optional.of("B")));
        log.info("Optional or notEmpty-{}", Optional.of("A").or(() -> Optional.of("B")));
        /*
        orElse
        有就直接返回T，否则返回orElse里面的T
        orElseGet：orElse()中无论Optional是否为空都会执行，orElseGet()只有当空的时候才会执行
         */
        log.info("Optional orElse empty-{}", Optional.empty().orElse("B"));
        log.info("Optional orElse notEmpty-{}", Optional.of("A").orElse("B"));
        /*
        orElseGet
        有就直接返回T，否则再通过Supplier接口获取一个T
         */
        log.info("Optional orElseGet empty-{}", Optional.empty().orElseGet(() -> "B"));
        log.info("Optional orElseGet notEmpty-{}", Optional.of("A").orElseGet(() -> "B"));

        /*
        orElseThrow
         */
        try {
//        log.info("Optional orElseThrow empty-{}", Optional.empty().orElseThrow());//java.util.NoSuchElementException: No value present
//        log.info("Optional orElseThrow empty-{}", Optional.empty().orElseThrow(()-> new Exception("异常啦")));//java.lang.Exception: 异常啦
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    void stream() {
        /*
        stream
         */
        Stream<Optional<String>> stream = Stream.of(Optional.of("Stream"));
        Stream<String> stringStream = stream.flatMap(Optional::stream);
        stringStream.forEach(x -> System.out.println(x));

        /*
        filter 推断通过为原实例，否则为empty
         */
        Optional empty2 = Optional.empty();
        Optional notEmpty2 = Optional.of("A");
        Optional notEmpty3 = Optional.of("B");
        log.info("Optional filter empty2-{}", empty2.filter(o -> "A".equals(o)));
        log.info("Optional filter noteEmpty2-{}", notEmpty2.filter(o -> "A".equals(o)));
        log.info("Optional filter noteEmpty3-{}", notEmpty3.filter(o -> "A".equals(o)));

        /*
        map 和 flatmap
         */
        Stream<String> urls = Stream.of("url:1", "url:2", "url:3", "url:4", "uri:1");
        Optional<String> url = urls.filter(s -> s.startsWith("url"))
                .findFirst()
                .map(String::toString);
        log.info("Optional map-{}", url.get());
        log.info("Optional flatMap-{}", url.flatMap(s -> Optional.of("first URL is " + s)));
    }
}
