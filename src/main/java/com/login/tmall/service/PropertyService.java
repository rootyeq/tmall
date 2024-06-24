package com.login.tmall.service;

import com.login.tmall.entity.Property;
import com.login.tmall.util.PageUtil;

import java.util.List;

public interface PropertyService {
    List<Property> getList(Property property, PageUtil pageUtil);
}
