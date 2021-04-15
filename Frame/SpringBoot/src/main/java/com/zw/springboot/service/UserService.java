package com.zw.springboot.service;

import com.zw.springboot.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> selectAllUser();
}
