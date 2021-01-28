package com.nongfu.kakax.basemain.services;

import com.nongfu.kakax.basemain.dao.UserDao;
import com.nongfu.kakax.basemain.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getUsers() {
        return userDao.selectUsers();
    }
}
