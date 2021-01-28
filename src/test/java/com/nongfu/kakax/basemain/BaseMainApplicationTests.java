package com.nongfu.kakax.basemain;

import com.nongfu.kakax.basemain.dao.UserDao;
import com.nongfu.kakax.basemain.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BaseMainApplicationTests {

    // @Autowired
    // SqlSessionFactoryBean sqlSessionFactoryBean;
    @Autowired
    SqlSession session;


    @Autowired
    UserDao userDao;

    @Test
    void testInterface() {
        List<User> list = userDao.selectUsers();
        System.out.println(list.size());
    }

    @Test
    void contextLoads() {
        // System.out.println(sqlSessionFactoryBean);
        List<User> users = session.selectList("com.nongfu.kakax.basemain.dao.UserDao.selectUsers");
        assertEquals(users.size(), 5);
        users.forEach( item -> {
            System.out.println(item.getId());
            System.out.println(item.getName());
            System.out.println(item.getEmail());
            System.out.println(item.getAge());
            System.out.println();
        });
        //SqlSessionUtils
    }

}
