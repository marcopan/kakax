package com.nongfu.kakax.basemain.dao;

import com.nongfu.kakax.basemain.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> selectUsers();
}
