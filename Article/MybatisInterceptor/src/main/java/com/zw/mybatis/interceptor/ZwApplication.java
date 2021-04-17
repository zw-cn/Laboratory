package com.zw.mybatis.interceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zw.mybatis.interceptor.mapper")
public class ZwApplication {
}
