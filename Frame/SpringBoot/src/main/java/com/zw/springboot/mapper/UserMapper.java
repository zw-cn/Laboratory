package com.zw.springboot.mapper;

import com.zw.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
        @Select("select * from users")
        List<User> selectAllUser();
}
