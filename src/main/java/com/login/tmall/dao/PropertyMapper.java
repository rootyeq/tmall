package com.login.tmall.dao;

import com.login.tmall.entity.Property;
import com.login.tmall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PropertyMapper {
    List<Property> select(@Param("property") Property property, @Param("pageUtil") PageUtil pageUtil);
}
