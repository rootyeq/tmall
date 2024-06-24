package com.login.tmall.service.impl;

import com.login.tmall.dao.CategoryMapper;
import com.login.tmall.entity.Category;

import com.login.tmall.service.CategoryService;
import com.login.tmall.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategoryById(Integer category_id) {
        return categoryMapper.getCategoryById(category_id);
    }

    @Override
    public Integer selectTotal(String category_name) {
        return categoryMapper.selectTotal(category_name);
    }

    @Override
    public List<Category> getListPage(String category_name, PageUtil pageUtil) {
        return categoryMapper.selectPage(category_name,pageUtil);
    }

    @Override
    public boolean add(Category category) {
        return categoryMapper.insertOne(category) > 0;
    }

    @Override
    public boolean delete(Integer category_id) {
        Integer i = categoryMapper.deleteCategory(category_id);
        if(i > 0){
            return  true;
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        return categoryMapper.updateCategory(category) > 0;
    }

}
