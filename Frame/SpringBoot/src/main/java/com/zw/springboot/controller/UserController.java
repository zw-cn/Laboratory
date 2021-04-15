package com.zw.springboot.controller;

import com.zw.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @RequestMapping("/{page}")
    public String showInfo(@PathVariable String page){
        log.info("select user info :");
        log.info(userServiceImpl.selectAllUser().toString());

        System.out.println("$$$$$$$$$44$$$$$$$");
        return page;
    }
}
