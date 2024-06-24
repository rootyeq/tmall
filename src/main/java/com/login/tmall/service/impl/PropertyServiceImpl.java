package com.login.tmall.service.impl;

import com.login.tmall.dao.PropertyMapper;
import com.login.tmall.entity.Property;
import com.login.tmall.service.PropertyService;
import com.login.tmall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Override
    public List<Property> getList(Property property, PageUtil pageUtil) {
        return propertyMapper.select(property,pageUtil);
    }
}
