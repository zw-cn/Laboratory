package com.zw.video.java8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.*;

/**
 * 重复注解及类型注解
 */
public class E21Annotation {
    /**
     * 类型注解
     * @param name
     */
    @ParameterizedTest
    @ValueSource(strings = {"hello"})
    void E21D1E1(@Java8AnnotationType String name) {
        System.out.println(name);
    }

    @Java8AnnotationRepeat("Hello")
    @Java8AnnotationRepeat("World")
    void E21D1E2() {

    }
    @Test
    void E21D1E2T() throws NoSuchMethodException {
        Class<E21Annotation> clazz = E21Annotation.class;
        Java8AnnotationRepeat[] repeats = clazz.getDeclaredMethod("E21D1E2").getAnnotationsByType(Java8AnnotationRepeat.class);
        System.out.println(repeats.length);
        Arrays.stream(repeats).forEach(System.out::println);
    }
}

/**
 * TYPE_PARAMETER 类型注解
 */
@Target({METHOD, PARAMETER, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface Java8AnnotationType {
    String value() default "zw-cn";
}

/**
 * 重复注解
 */
@Target({METHOD, PARAMETER, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Java8AnnotationRepeats.class)
@interface Java8AnnotationRepeat {
    String value() default "repeat";
}
/**
 * 可重复注解容器
 */
@Target({METHOD, PARAMETER, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface Java8AnnotationRepeats {
    Java8AnnotationRepeat[] value();
}
