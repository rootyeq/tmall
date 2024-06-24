package com.login.tmall.service;

import com.login.tmall.entity.User;
import com.login.tmall.util.OrderUtil;
import com.login.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User login(String username, String password);
    User getUser(String username, String password);
    Boolean register(User user);


    List<User> getAdminSelect(User user, OrderUtil orderUtil, PageUtil pageUtil);
}
