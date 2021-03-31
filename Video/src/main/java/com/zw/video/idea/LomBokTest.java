package com.zw.video.idea;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LomBokTest {
    @Test
    void testSetterGetter() throws CloneNotSupportedException {
        LombokMan1 bean = new LombokMan1();
        //测试setter
        bean.setName("lombok");
        bean.setAge(18);
        bean.setSalary(12000.5);
        //测试getter
        System.out.println(bean.getName());
        System.out.println(bean.getAge());
        System.out.println(bean.getSalary());
        //测试toString
        System.out.println(bean.toString());
        //测试hashcode和equals
        System.out.println(bean.hashCode());
        //注释@EqualsAndHashCode 打印false,否则打印true -> @EqualsAndHashCode重写了 hashcode和equals方法
        System.out.println(bean.equals(bean.clone()));
    }

    @Test
    void testData() throws CloneNotSupportedException {
        LombokMan2 bean = new LombokMan2();
        //测试setter
        bean.setName("lombok");
        bean.setAge(18);
        bean.setSalary(12000.5);
        //测试getter
        System.out.println(bean.getName());
        System.out.println(bean.getAge());
        System.out.println(bean.getSalary());
        //测试toString
        System.out.println(bean.toString());
        //测试hashcode
        System.out.println(bean.hashCode());
        System.out.println(bean.equals(bean.clone()));
    }

    @Test
    void testBuild() {
        LombokMan3 lombokMan3 = LombokMan3.builder().name("Jerry").age(6).salary(999_999_999.999).build();
        System.out.println(lombokMan3);
    }

    @Test
    void testSlf4j() {
        LombokMan4 lombokMan4 = new LombokMan4();
        lombokMan4.logs();
    }

    @Test
    void testNonNull() {
        LombokMan4 lombokMan4 = new LombokMan4();
        lombokMan4.nonNull(null);
    }
}

/**
 * 使用@Setter @Getter注解
 */
@Getter
@Setter
@ToString
//@EqualsAndHashCode
class LombokMan1 implements Cloneable {
    private String name;
    private Integer age;
    private Double salary;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

/**
 * 使用@Data注解
 * 相当与@Getter @Setter @ToString
 * 加上重写hashcode和equals方法
 */
@Data
class LombokMan2 implements Cloneable {
    private String name;
    private Integer age;
    private Double salary;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

/**
 * 使用@Builder注解
 * 建造构造器
 */
@Builder
@ToString
class LombokMan3 {
    private String name;
    private Integer age;
    private Double salary;
}

@Slf4j
class LombokMan4 {
    void logs() {
        log.error("Test @Slf4j");
    }

    void nonNull(@NonNull String message) {
        System.out.println(message);
    }
}