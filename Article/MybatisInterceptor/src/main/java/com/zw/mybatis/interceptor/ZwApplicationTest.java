package com.zw.mybatis.interceptor;


import com.zw.mybatis.interceptor.mapper.UsersMapper;
import com.zw.mybatis.interceptor.pojo.Users;
import com.zw.mybatis.interceptor.pojo.UsersExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(SpringRun)
@Slf4j
@SpringBootTest(classes = ZwApplication.class)
public class ZwApplicationTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testInsert() {
        Users users = Users.builder().id(1).name("张三").password("1234").build();
        usersMapper.insert(users);
    }

    @Test
    public void testUpdate() {
        Users user = Users.builder().name("Alin").build();
        UsersExample example = new UsersExample();
        example.createCriteria().andIdEqualTo(1);
        usersMapper.updateByExampleSelective(user, example);

        log.info(usersMapper.selectByPrimaryKey(1).toString());
    }
}
