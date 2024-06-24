package com.login.tmall.service;

import com.login.tmall.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminService {
    Admin login( String username, String password);
}
