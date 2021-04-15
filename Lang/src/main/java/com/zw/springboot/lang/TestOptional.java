package com.zw.springboot.lang;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * 测试 Optional
 */
public class TestOptional {
    @Test
    void test() {
        Object s = Optional.ofNullable(null).orElse("ok");
        System.out.println(s);
    }
}
