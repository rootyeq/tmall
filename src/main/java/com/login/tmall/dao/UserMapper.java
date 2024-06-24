package com.login.tmall.dao;

import com.login.tmall.entity.User;
import com.login.tmall.util.OrderUtil;
import com.login.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByUserAndPassword(@Param("username") String username, @Param("password") String password);

    User getUser(@Param("username") String username, @Param("password") String password);
    int insertOne(User user);


    /**
     * 获取管理用户列表
     */

    List<User> AdminSelect(@Param("user") User user, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
}
