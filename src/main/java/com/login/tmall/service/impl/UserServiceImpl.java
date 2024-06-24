package com.login.tmall.service.impl;

import com.login.tmall.dao.UserMapper;
import com.login.tmall.entity.User;
import com.login.tmall.service.UserService;
import com.login.tmall.util.OrderUtil;
import com.login.tmall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper UserMapper;


    @Override
    public User login(String username, String password) {
        return UserMapper.getUserByUserAndPassword(username,password);
    }

    @Override
    public User getUser(String username, String password) {
        return UserMapper.getUser(username,password);
    }

    @Override
    public Boolean register(User user) {
        int row = UserMapper.insertOne(user);
        if (row >0){
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAdminSelect(User user, OrderUtil orderUtil, PageUtil pageUtil) {
        return UserMapper.AdminSelect(user,orderUtil,pageUtil);
    }


}
