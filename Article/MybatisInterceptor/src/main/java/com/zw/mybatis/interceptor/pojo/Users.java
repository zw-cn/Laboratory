package com.zw.mybatis.interceptor.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {
    private Integer id;

    private String name;

    private String password;

    private String enPassword;

}