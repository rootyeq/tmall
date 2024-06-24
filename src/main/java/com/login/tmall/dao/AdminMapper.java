package com.login.tmall.dao;

import com.login.tmall.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    Admin getAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
