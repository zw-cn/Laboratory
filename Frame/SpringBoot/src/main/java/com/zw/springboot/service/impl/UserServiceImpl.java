package com.zw.springboot.service.impl;

import com.zw.springboot.mapper.UserMapper;
import com.zw.springboot.pojo.User;
import com.zw.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectAllUser() {

        return userMapper.selectAllUser();
    }
}
