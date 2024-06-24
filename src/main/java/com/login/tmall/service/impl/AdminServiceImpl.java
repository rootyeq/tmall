package com.login.tmall.service.impl;

import com.login.tmall.dao.AdminMapper;
import com.login.tmall.entity.Admin;
import com.login.tmall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String username, String password) {
        return adminMapper.getAdminByUsernameAndPassword(username,password);
    }
}
